package eci.edu.dows.profesorSuperO.service.Acciones;


import eci.edu.dows.profesorSuperO.Util.Exceptions.CrossGroupsException;
import eci.edu.dows.profesorSuperO.Util.Exceptions.InvalidRequestTypeException;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Exceptions.StudentWithoutScheduleException;
import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.Horario;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioGrupo;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioMateria;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.GrupoRepository;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
@Slf4j
public class AceptarSolicitud implements AccionSolicitudCommand {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final MateriaRepository materiaRepository;
    private final SolicitudRepository solicitudRepository;

    @Override
    public void accionSolicitud(Solicitud solicitud) {
        Estudiante e = estudianteRepository.findById(solicitud.getEstudianteId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        if (solicitud instanceof SolicitudCambioGrupo cambioGrupo) {
            aceptarSolicitudGrupo(cambioGrupo, e);
            solicitud.setEstado(EstadoSolicitud.APROBADA);
            log.info("✅ Solicitud de cambio de grupo aprobada para {}", e.getId());
        }
        else if (solicitud instanceof SolicitudCambioMateria cambioMateria) {
            aceptarSolicitudMateria(cambioMateria, e);
            solicitud.setEstado(EstadoSolicitud.APROBADA);
            log.info("✅ Solicitud de cambio de materia aprobada para {}", e.getId());
        }
        else {
            throw new InvalidRequestTypeException("Tipo de solicitud incorrecto para AceptarSolicitud");
        }
    }


    private void aceptarSolicitudGrupo(SolicitudCambioGrupo solicitudCambio, Estudiante estudiante) {
        if (estudiante.getHorarios().isEmpty()) {
            throw new StudentWithoutScheduleException("El estudiante no tiene horarios ");
        }

        materiaRepository.findById(solicitudCambio.getMateriaProblemaId()).orElseThrow(()-> new NotFoundException("Materia solicitada no encontrada"));


        Horario horario = estudiante.getHorarios().get(estudiante.getHorarios().size() - 1);

        Grupo grupoNuevo = grupoRepository.findById(solicitudCambio.getGrupoCambioId()).orElseThrow(()-> new NotFoundException("Grupo no encontrado"));

        if(tieneConflictoDeHorarios(estudiante,grupoNuevo)){
            throw new CrossGroupsException("El nuevo grupo se superpone con otro horario existente.");
        }else{
            Grupo grupoViejo = grupoRepository.findById(solicitudCambio.getGrupoId()).orElseThrow(()-> new NotFoundException("Grupo no encontrado"));
            horario.getGrupos().remove(grupoViejo);
            horario.getGrupos().add(grupoNuevo);

            int ultimo = estudiante.getHorarios().size() - 1;
            estudiante.getHorarios().set(ultimo, horario);
            estudianteRepository.save(estudiante);
        }
    }


    private void aceptarSolicitudMateria(SolicitudCambioMateria solicitudCambio, Estudiante estudiante) {
        if (estudiante.getHorarios().isEmpty()) {
            throw new StudentWithoutScheduleException("El estudiante no tiene horarios ");
        }
        Horario horario = estudiante.getHorarios().get(estudiante.getHorarios().size() - 1);

        Materia materiaNueva = materiaRepository.findById(solicitudCambio.getMateriaCambioId())
                .orElseThrow(() -> new NotFoundException("Materia nueva no encontrada"));

        Materia materiaVieja = materiaRepository.findById(solicitudCambio.getMateriaProblemaId())
                .orElseThrow(() -> new NotFoundException("Materia vieja no encontrada"));

        List<Grupo> gruposMateriaNueva = grupoRepository.findByMateria_id(materiaNueva.getId());
        if (gruposMateriaNueva.isEmpty()) {
            throw new NotFoundException("No existen grupos para la materia nueva seleccionada.");
        }

        horario.getGrupos().removeIf(g -> g.getMateria().getId().equals(materiaVieja.getId()));

        Grupo grupoNuevo;
        if (solicitudCambio.getGrupoCambioId() != null) {
            grupoNuevo = grupoRepository.findById(solicitudCambio.getGrupoCambioId())
                    .orElseThrow(() -> new NotFoundException("Grupo nuevo no encontrado"));
        } else {
            throw new InvalidRequestTypeException("Grupo nuevo no especificado para el cambio");
        }


        if (tieneConflictoDeHorarios(estudiante, grupoNuevo)) {
            throw new CrossGroupsException("El grupo de la nueva materia tiene conflicto  con otro horario");
        }


        Grupo grupoViejo = grupoRepository.findById(solicitudCambio.getGrupoId())
                .orElseThrow(() -> new NotFoundException("Grupo viejo no encontrado"));
        grupoViejo.setCupo(grupoViejo.getCupo() + 1);
        grupoRepository.save(grupoViejo);


        horario.getGrupos().add(grupoNuevo);
        estudiante.getHorarios().set(estudiante.getHorarios().size() - 1, horario);
        estudianteRepository.save(estudiante);


        grupoNuevo.setCupo(grupoNuevo.getCupo() - 1);
        grupoRepository.save(grupoNuevo);




    }







    private boolean tieneConflictoDeHorarios(Estudiante estudiante, Grupo nuevoGrupo) {
        for (Horario horario : estudiante.getHorarios()) {
            for (Grupo grupoExistente : horario.getGrupos()) {
                for (Clase claseExistente : grupoExistente.getClases()) {
                    for (Clase claseNueva : nuevoGrupo.getClases()) {
                        if (grupoExistente.getId().equals(nuevoGrupo.getId())) continue;
                        if (claseExistente.getDiaSemana() == claseNueva.getDiaSemana()) {
                            boolean seSuperponen =
                                    claseNueva.getHoraInicio().isBefore(claseExistente.getHoraFin()) &&
                                            claseNueva.getHoraFin().isAfter(claseExistente.getHoraInicio());
                            if (seSuperponen) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }








    @Override
    public AccionesSolicitud getTipoSolicitud() {
        return AccionesSolicitud.ACEPTAR;
    }

}

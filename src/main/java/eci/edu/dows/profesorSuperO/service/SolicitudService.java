package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.SolicitudCambioGrupoMapper;
import eci.edu.dows.profesorSuperO.Util.SolicitudCambioMateriaMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.repository.*;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final DecanaturaRepository decanaturaRepository;
    private final EstudianteRepository estudianteRepository;
    private final Validator validator;
    private CalendarioRepository calendarioRepository;
    private MateriaRepository materiaRepository;
    private GrupoRepository grupoRepository;
    private final SolicitudCambioGrupoMapper SolicitudCambioGrupoMapper;
    private final SolicitudCambioMateriaMapper solicitudCambioMateriaMapper;
    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository,
                            DecanaturaRepository decanaturaRepository,
                            EstudianteRepository estudianteRepository,
                            Validator validator,SolicitudCambioGrupoMapper solicitudCambioGrupoMapper,
                            SolicitudCambioMateriaMapper solicitudCambioMateriaMapper) {
        this.solicitudRepository = solicitudRepository;
        this.decanaturaRepository = decanaturaRepository;
        this.estudianteRepository = estudianteRepository;
        this.validator = validator;
        this.SolicitudCambioGrupoMapper = solicitudCambioGrupoMapper;
        this.solicitudCambioMateriaMapper = solicitudCambioMateriaMapper;
    }

    public SolicitudCambioGrupoDTO crearSolicitudCambioGrupo(SolicitudCambioGrupoDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudiante().getId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Materia materia = materiaRepository.findById(dto.getMateriaProblema().getId())
                .orElseThrow(() -> new NotFoundException("Materia no encontrada"));
        Grupo grupo = grupoRepository.findById(dto.getGrupo().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo actual no encontrado"));

        Grupo grupoCambio = grupoRepository.findById(dto.getGrupoCambio().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo de cambio no encontrado"));

        SolicitudCambioGrupo solicitud = SolicitudCambioGrupoMapper.toEntity(dto);
        solicitud.setEstudiante(estudiante);
        solicitud.setMateriaProblema(materia);
        solicitud.setGrupo(grupo);
        solicitud.setGrupoCambio(grupoCambio);

        return  SolicitudCambioGrupoMapper.toDTO(solicitudRepository.save(solicitud));

    }




    public SolicitudCambioMateriaDTO crearSolicitudCambioMateria(SolicitudCambioMateriaDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudiante().getId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Materia materiaProblema = materiaRepository.findById(dto.getMateriaProblema().getId())
                .orElseThrow(() -> new NotFoundException("Materia no encontrada"));

        Materia materiaNueva = materiaRepository.findById(dto.getMateriaCambio().getId()).
                orElseThrow(() -> new NotFoundException("Materia no encontrada"));
        Grupo grupo = grupoRepository.findById(dto.getGrupo().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo actual no encontrado"));

        Grupo grupoCambio = grupoRepository.findById(dto.getGrupoCambio().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo de cambio no encontrado"));

        SolicitudCambioMateria solicitud = solicitudCambioMateriaMapper.toEntity(dto);
        solicitud.setEstudiante(estudiante);
        solicitud.setMateriaProblema(materiaProblema);
        solicitud.setGrupo(grupo);
        solicitud.setGrupoCambio(grupoCambio);
        solicitud.setMateriaCambio(materiaNueva);

        return solicitudCambioMateriaMapper.toDTO(solicitudRepository.save(solicitud));
    }

    public List<Solicitud> consultarSolicitudes() {
        return solicitudRepository.findAll();
    }

    public Solicitud consultarSolicitudPorId(String id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    public Solicitud actualizarEstadoSolicitud(String id, EstadoSolicitud nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.setEstado(nuevoEstado);
        return solicitudRepository.save(solicitud);
    }

    public Decanatura enviarSolicitudDecanatura(String decanaturaId, Solicitud solicitud) {
        solicitudRepository.save(solicitud);

        Decanatura decanatura = decanaturaRepository.findById(decanaturaId)
                .orElseThrow(() -> new RuntimeException("Decanatura no encontrada"));
        decanatura.getSolicitudes().add(solicitud);

        return decanaturaRepository.save(decanatura);
    }

    public Estudiante enviarSolicitudEstudiante(String estudianteId, Solicitud solicitud) {
        solicitudRepository.save(solicitud);

        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("estudiante no encontrado"));
        estudiante.getSolicitudes().add(solicitud);

        return estudianteRepository.save(estudiante);
    }


    public void eliminarSolicitud(String id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitudRepository.delete(solicitud);
    }
}


package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.repository.DecanaturaRepository;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final DecanaturaRepository decanaturaRepository;
    private final EstudianteRepository estudianteRepository;

    public SolicitudService(SolicitudRepository solicitudRepository,
                            DecanaturaRepository decanaturaRepository,
                            EstudianteRepository estudianteRepository) {
        this.solicitudRepository = solicitudRepository;
        this.decanaturaRepository = decanaturaRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public Solicitud crearSolicitudCambioGrupo(SolicitudCambioGrupoDTO dto) {
        SolicitudCambioGrupo solicitud = new SolicitudCambioGrupo(
                dto.getId(),
                dto.getEstudiante(),
                dto.getMotivo(),
                dto.getFecha(),
                dto.getMateriaProblema(),
                dto.getGrupo(),
                dto.getGrupoCambio()
        );
        return solicitudRepository.save(solicitud);
    }


    public Solicitud crearSolicitudCambioMateria(SolicitudCambioMateriaDTO dto) {
        SolicitudCambioGrupo solicitud = new SolicitudCambioGrupo(dto.getId(),
                dto.getEstudiante(),
                dto.getMotivo(),
                dto.getFecha(),
                dto.getMateriaProblema(),
                dto.getGrupo(),
                dto.getGrupoCambio());
        return solicitudRepository.save(solicitud);
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


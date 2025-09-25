package eci.edu.dows.profesorSuperO.service;


import eci.edu.dows.profesorSuperO.model.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Facultades;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DecanaturaService {
    private final SolicitudRepository solicitudRepository;
    private final AceptarSolicitud aceptarSolicitud;
    private final DeclinarSolicitud rechazarSolicitud;

    public DecanaturaService(SolicitudRepository solicitudRepository,
                             AceptarSolicitud aceptarSolicitud,
                             DeclinarSolicitud rechazarSolicitud) {
        this.solicitudRepository = solicitudRepository;
        this.aceptarSolicitud = aceptarSolicitud;
        this.rechazarSolicitud = rechazarSolicitud;
    }

    public List<Solicitud> obtenerSolicitudesPorFacultad(Facultades facultad) {
        return solicitudRepository.findByEstudiante_facultad(facultad);
    }

    public Solicitud cambiarEstado(String solicitudId, AccionSolicitudCommand accion) {
        Optional<Solicitud> solicitudOpt = solicitudRepository.findById(solicitudId);
        if (solicitudOpt.isPresent()) {
            Solicitud solicitud = solicitudOpt.get();
            accion.accionSolicitud(solicitud);
            return solicitudRepository.save(solicitud);
        } else {
            throw new RuntimeException("Solicitud no encontrada con ID: " + solicitudId);
        }
    }


    public List<Solicitud> obtenerSolicitudesPorPrioridad(int prioridad) {
        return solicitudRepository.findByPrioridad(prioridad);
    }

    public List<Solicitud> obtenerSolicitudesPendientes(Facultades facultad) {
        List<Solicitud> todas = solicitudRepository.findByEstudiante_facultad(facultad);
        return todas.stream()
                .filter(s -> s.getEstado() == EstadoSolicitud.PENDIENTE)
                .toList();
    }
}

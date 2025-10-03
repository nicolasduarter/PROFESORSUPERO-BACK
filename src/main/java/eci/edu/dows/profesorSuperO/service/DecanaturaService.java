package eci.edu.dows.profesorSuperO.service;


import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudCommand;
import eci.edu.dows.profesorSuperO.service.Acciones.AceptarSolicitud;
import eci.edu.dows.profesorSuperO.service.Acciones.DeclinarSolicitud;
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

    public List<Solicitud> obtenerSolicitudesPorFacultad(Facultad facultad) {
        return solicitudRepository.findByFacultad(facultad);
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

    public List<Solicitud> obtenerSolicitudesPendientes(Facultad facultad) {
        List<Solicitud> todas = solicitudRepository.findByFacultad(facultad);
        return todas.stream()
                .filter(s -> s.getEstado() == EstadoSolicitud.PENDIENTE)
                .toList();
    }
}

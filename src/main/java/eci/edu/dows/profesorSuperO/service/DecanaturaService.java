package eci.edu.dows.profesorSuperO.service;


import eci.edu.dows.profesorSuperO.model.Facultades;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class DecanaturaService {
    private final SolicitudRepository solicitudRepository;

    public DecanaturaService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public List<Solicitud> obtenerSolicitudesPorFacultad(Facultades facultad) {
        return solicitudRepository.findByEstudiante_facultad(facultad);
    }


}

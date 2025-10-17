package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.*;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final SolicitudRepository solicitudRepository;
    private final SemaforoService  semaforoService;
    private final HorarioMapper  horarioMapper;
    private final SolicitudMapper solicitudMapper;
    private final ProfesorMapper profesorMapper;
    private final GrupoMapper grupoMapper;

    public ReporteService(EstudianteRepository estudianteRepository,
                            GrupoRepository grupoRepository,
                            SolicitudRepository solicitudRepository,
                            SemaforoService semaforoService,
                            HorarioMapper horarioMapper,
                            SolicitudMapper solicitudMapper,
                            ProfesorMapper profesorMapper,
                            GrupoMapper grupoMapper) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
        this.solicitudRepository = solicitudRepository;
        this.semaforoService = semaforoService;
        this.horarioMapper = horarioMapper;
        this.solicitudMapper = solicitudMapper;
        this.grupoMapper = grupoMapper;
        this.profesorMapper = profesorMapper;
    }

    public float tasaAprovacionSolicitudes() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        if (solicitudes.isEmpty()) {
            return 0;
        }
        List<Solicitud> solicitudesAprobadas = solicitudRepository.findByEstado(EstadoSolicitud.APROBADA);
        return (float) solicitudesAprobadas.size() / (float) solicitudes.size();
    }

    public float tasaRechazoSolicitudes() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        if (solicitudes.isEmpty()) {
            return 0;
        }
        List<Solicitud> solicitudesRechazadas = solicitudRepository.findByEstado(EstadoSolicitud.RECHAZADA);
        return (float) solicitudesRechazadas.size() / (float) solicitudes.size();
    }

    public List<Grupo> gruposMasSolicitados(int grupos) {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        Map<Grupo, Integer> conteoGrupos = new HashMap<>();

        for (Solicitud solicitud : solicitudes) {
            Grupo grupoCambio = null;

            if (solicitud instanceof SolicitudCambioGrupo cambioGrupo) {
                grupoCambio = cambioGrupo.getGrupoCambio();
            } else if (solicitud instanceof SolicitudCambioMateria cambioMateria) {
                grupoCambio = cambioMateria.getGrupoCambio();
            }

            if (grupoCambio != null) {
                conteoGrupos.put(grupoCambio, conteoGrupos.getOrDefault(grupoCambio, 0) + 1);
            }
        }

        return conteoGrupos.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(grupos)
                .map(Map.Entry::getKey)
                .toList();
    }
}
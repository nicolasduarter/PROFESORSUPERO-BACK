package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import eci.edu.dows.profesorSuperO.service.DecanaturaService;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decanatura")
public class DecanaturaController {

    private final DecanaturaService decanaturaService;

    public DecanaturaController(DecanaturaService decanaturaService) {
        this.decanaturaService = decanaturaService;
    }

    @GetMapping("/solicitudes/facultad/{facultad}")
    public List<SolicitudDTO> obtenerSolicitudesPorFacultad(@PathVariable FacultadDTO facultad) {
        return decanaturaService.obtenerSolicitudesPorFacultad(facultad);
    }

    @GetMapping("/solicitudes/prioridad/{prioridad}")
    public List<SolicitudDTO> obtenerSolicitudesPorPrioridad(@PathVariable int prioridad) {
        return decanaturaService.obtenerSolicitudesPorPrioridad(prioridad);
    }

    @GetMapping("/solicitudes/pendientes/facultad/{facultad}")
    public List<SolicitudDTO> obtenerSolicitudesPendientes(@PathVariable FacultadDTO facultad) {
        return decanaturaService.obtenerSolicitudesPendientes(facultad);
    }

    @PutMapping("/solicitudes/{solicitudId}/accion")
    public SolicitudDTO cambiarEstadoSolicitud(@PathVariable String solicitudId,
                                            @RequestBody String accion) {
        return decanaturaService.cambiarEstado(solicitudId, accion);
    }
}


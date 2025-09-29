package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
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
    public List<Solicitud> obtenerSolicitudesPorFacultad(@PathVariable Facultades facultad) {
        return decanaturaService.obtenerSolicitudesPorFacultad(facultad);
    }

    @GetMapping("/solicitudes/prioridad/{prioridad}")
    public List<Solicitud> obtenerSolicitudesPorPrioridad(@PathVariable int prioridad) {
        return decanaturaService.obtenerSolicitudesPorPrioridad(prioridad);
    }

    @GetMapping("/solicitudes/pendientes/facultad/{facultad}")
    public List<Solicitud> obtenerSolicitudesPendientes(@PathVariable Facultades facultad) {
        return decanaturaService.obtenerSolicitudesPendientes(facultad);
    }

    @PutMapping("/solicitudes/{solicitudId}/accion")
    public Solicitud cambiarEstadoSolicitud(@PathVariable String solicitudId,
                                            @RequestBody AccionSolicitudCommand accion) {
        return decanaturaService.cambiarEstado(solicitudId, accion);
    }
}


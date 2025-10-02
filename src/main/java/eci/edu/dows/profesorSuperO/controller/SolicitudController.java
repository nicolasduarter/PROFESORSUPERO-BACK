package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/cambio-grupo")
    public SolicitudCambioGrupoDTO crearSolicitudCambioGrupo(@Valid @RequestBody SolicitudCambioGrupoDTO dto) {
        return solicitudService.crearSolicitudCambioGrupo(dto);
    }

    @PostMapping("/cambio-materia")
    public SolicitudCambioMateriaDTO crearSolicitudCambioMateria(@Valid @RequestBody SolicitudCambioMateriaDTO dto) {
        return solicitudService.crearSolicitudCambioMateria(dto);
    }

    @GetMapping
    public List<Solicitud> consultarSolicitudes() {
        return solicitudService.consultarSolicitudes();
    }

    @GetMapping("/{id}")
    public Solicitud consultarSolicitudPorId(@PathVariable String id) {
        return solicitudService.consultarSolicitudPorId(id);
    }

    @PutMapping("/{id}/estado")
    public Solicitud actualizarEstadoSolicitud(@PathVariable String id,
                                               @RequestParam EstadoSolicitud nuevoEstado) {
        return solicitudService.actualizarEstadoSolicitud(id, nuevoEstado);
    }

    @PostMapping("/{decanaturaId}/enviar-decanatura")
    public Decanatura enviarSolicitudDecanatura(@PathVariable String decanaturaId,
                                                @RequestBody Solicitud solicitud) {
        return solicitudService.enviarSolicitudDecanatura(decanaturaId, solicitud);
    }

    @PostMapping("/{estudianteId}/enviar-estudiante")
    public Estudiante enviarSolicitudEstudiante(@PathVariable String estudianteId,
                                                @RequestBody Solicitud solicitud) {
        return solicitudService.enviarSolicitudEstudiante(estudianteId, solicitud);
    }

    @DeleteMapping("/{id}")
    public void eliminarSolicitud(@PathVariable String id) {
        solicitudService.eliminarSolicitud(id);
    }
}


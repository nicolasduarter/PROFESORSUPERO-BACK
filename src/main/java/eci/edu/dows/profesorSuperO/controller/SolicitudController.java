package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/cambio-grupo")
    public Solicitud crearSolicitudCambioGrupo(@RequestParam String id,
                                               @RequestBody Estudiante estudiante,
                                               @RequestParam String motivo,
                                               @RequestParam String fecha,
                                               @RequestBody Materia materiaProblema,
                                               @RequestBody Grupo grupo,
                                               @RequestBody Grupo grupoCambio) {
        return solicitudService.crearSolicitudCambiogrupo(
                id, estudiante, motivo, LocalDate.parse(fecha), materiaProblema, grupo, grupoCambio
        );
    }

    @PostMapping("/cambio-materia")
    public Solicitud crearSolicitudCambioMateria(@RequestParam String id,
                                                 @RequestBody Estudiante estudiante,
                                                 @RequestParam String motivo,
                                                 @RequestParam String fecha,
                                                 @RequestBody Materia materiaProblema,
                                                 @RequestBody Materia materiaCambio,
                                                 @RequestBody Grupo grupo,
                                                 @RequestBody Grupo grupoCambio) {
        return solicitudService.crearSolicitudCambioMateria(
                id, estudiante, motivo, LocalDate.parse(fecha),
                materiaProblema, materiaCambio, grupo, grupoCambio
        );
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


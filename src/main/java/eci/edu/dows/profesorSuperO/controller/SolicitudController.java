package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 Controlador de Solicitudes
 Permite crear Solicitudes según el tipo
 Se pueden obtener las solicitudes
 Y se pueden eliminar
 Ninguna solicitud puede ser editada en este controlador
 Esta funcionalidad la puede usar cualquier rol
 */

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/cambio-grupo")
    public ResponseEntity<SolicitudCambioGrupoDTO> crearSolicitudCambioGrupo(@Valid @RequestBody SolicitudCambioGrupoDTO dto) {
        return ResponseEntity.ok(solicitudService.crearSolicitudCambioGrupo(dto));
    }

    @PostMapping("/cambio-materia")
    public ResponseEntity<SolicitudCambioMateriaDTO> crearSolicitudCambioMateria(@Valid @RequestBody SolicitudCambioMateriaDTO dto) {
        return  ResponseEntity.ok(solicitudService.crearSolicitudCambioMateria(dto));
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> consultarSolicitudes() {
        return  ResponseEntity.ok(solicitudService.consultarSolicitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> consultarSolicitudPorId(@PathVariable String id) {
        return  ResponseEntity.ok(solicitudService.consultarSolicitudPorId(id));
    }

    @DeleteMapping("/{id}")
    public void eliminarSolicitud(@PathVariable String id) {
        solicitudService.eliminarSolicitud(id);
    }

    @PatchMapping("/{solicitudId}/respuesta")
    public ResponseEntity<SolicitudDTO> responderInformacionAdicional(@PathVariable String solicitudId, @RequestBody String texto) {
        return ResponseEntity.ok(solicitudService.responderInformacionAdicional(solicitudId, texto));
    }

}


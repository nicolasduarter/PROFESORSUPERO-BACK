package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import eci.edu.dows.profesorSuperO.service.DecanaturaService;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudCommand;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;


import java.util.List;



/**
 Controlador de Decanatura
 Permite obtener solicitudes de su facultad
 Permite cambiar el estado de una solicitud
 Solo para el rol de decanatura
 */
@RestController
@RequestMapping("/decanatura")
public class DecanaturaController {

    private final DecanaturaService decanaturaService;


    public DecanaturaController(DecanaturaService decanaturaService) {
        this.decanaturaService = decanaturaService;
    }

    @GetMapping("/solicitudes/facultad/{facultadId}")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPorFacultad(@PathVariable String facultadId) {
        FacultadDTO facultadDTO = new FacultadDTO();
        facultadDTO.setId(facultadId);
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPorFacultad(facultadDTO));
    }


    @GetMapping("/solicitudes/prioridad/{prioridad}")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPorPrioridad(@PathVariable int prioridad) {
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPorPrioridad(prioridad));
    }

    @GetMapping("/solicitudes/pendientes/facultad/{facultadId}")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPendientes(@PathVariable String facultadId) {
        FacultadDTO facultadDTO = new FacultadDTO();
        facultadDTO.setId(facultadId);
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPendientes(facultadDTO));
    }


    @PatchMapping("/solicitudes/{solicitudId}/accion")
    public ResponseEntity<SolicitudDTO> cambiarEstadoSolicitud(@PathVariable String solicitudId,
                                            @RequestBody String accion) {
        return ResponseEntity.ok(decanaturaService.cambiarEstado(solicitudId, accion));
    }

    @GetMapping("/estudiantes/{estudianteId}")
    public ResponseEntity<EstudianteDTO> verInformacionEstudiante(@PathVariable String estudianteId) {
        EstudianteDTO estudianteDTO = decanaturaService.verInformacionEstudiante(estudianteId);
        return ResponseEntity.ok(estudianteDTO);
    }

}


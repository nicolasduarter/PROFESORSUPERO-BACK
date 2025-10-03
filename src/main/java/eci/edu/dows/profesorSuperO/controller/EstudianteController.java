package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.service.SemaforoService;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Estudiantes")
public class EstudianteController {
    private final SolicitudService solicitudService;

    public EstudianteController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }


    @PostMapping("/Solicitud/Cambio-Materia")
    public ResponseEntity<SolicitudCambioMateriaDTO> crearSolicitudCambioMateria(@Valid @RequestBody SolicitudCambioMateriaDTO dto) {

        return ResponseEntity.ok(solicitudService.crearSolicitudCambioMateria(dto));
    }

    @PostMapping("/Solicitud/Cambio-Grupo")
    public ResponseEntity<SolicitudCambioGrupoDTO> crearSolicitudCambioGrupo(@Valid @RequestBody SolicitudCambioGrupoDTO dto) {
        return ResponseEntity.ok(solicitudService.crearSolicitudCambioGrupo(dto));
    }





}

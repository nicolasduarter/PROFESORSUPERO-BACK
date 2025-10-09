package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.service.SemaforoService;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import eci.edu.dows.profesorSuperO.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final SolicitudService solicitudService;
    private final UsuarioService usuarioService;

    @Autowired
    public EstudianteController(SolicitudService solicitudService, UsuarioService usuarioService) {
        this.solicitudService = solicitudService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public EstudianteDTO crearEstudiante(@RequestBody EstudianteDTO dto) {
        return  usuarioService.crearEstudiante(dto);
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

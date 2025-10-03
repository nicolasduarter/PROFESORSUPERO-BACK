package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.service.GrupoService;
import org.springframework.web.bind.annotation.*;



/**
 Controlador de grupos
 Tiene el CRUD completo para los grupos
 Exlusivo para rol {Admin}
 */
@RestController
@RequestMapping("/api/Grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/crearGrupo")
    public GrupoDTO crearGrupo(@RequestBody GrupoDTO dto) {
        return grupoService.crearGrupo(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarGrupo(@PathVariable String id) {
        grupoService.eliminarGrupo(id);
    }

    @PatchMapping("/modificarCupo")
    public GrupoDTO modificarCuposGrupo(@RequestParam String grupoId,
                                     @RequestParam int cupo) {
        return grupoService.modificarCuposGrupo(grupoId, cupo);
    }

    @PatchMapping("/agregarEstudiante")
    public GrupoDTO agregarEstudianteAGrupo(@RequestParam String grupoId,
                                         @RequestParam String estudianteId) {
        return grupoService.agregarEstudianteAGrupo(grupoId, estudianteId);
    }

    @DeleteMapping("/{grupoId}")
    public GrupoDTO eliminarEstudianteAGrupo(@RequestParam String grupoId,
                                          @RequestParam String estudianteId) {
        return grupoService.eliminarEstudianteAGrupo(grupoId, estudianteId);
    }
}



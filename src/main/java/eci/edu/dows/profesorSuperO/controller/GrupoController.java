package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.service.GrupoService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/Grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/crearGrupo")
    public Grupo crearGrupo(@RequestBody GrupoDTO dto) {
        return grupoService.crearGrupo(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarGrupo(@PathVariable String id) {
        grupoService.eliminarGrupo(id);
    }

    @PutMapping("/modificarCupo")
    public Grupo modificarCuposGrupo(@RequestParam String grupoId,
                                     @RequestParam int cupo) {
        return grupoService.modificarCuposGrupo(grupoId, cupo);
    }

    @PutMapping("/agregarEstudiante")
    public Grupo agregarEstudianteAGrupo(@RequestParam String grupoId,
                                         @RequestParam String estudianteId) {
        return grupoService.agregarEstudianteAGrupo(grupoId, estudianteId);
    }

    @DeleteMapping("/{grupoId}")
    public Grupo eliminarEstudianteAGrupo(@RequestParam String grupoId,
                                          @RequestParam String estudianteId) {
        return grupoService.eliminarEstudianteAGrupo(grupoId, estudianteId);
    }
}



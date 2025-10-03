package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.service.GrupoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/crear")
    public GrupoDTO crearGrupo(@RequestBody GrupoDTO dto) {
        return grupoService.crearGrupo(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarGrupo(@PathVariable String id) {
        grupoService.eliminarGrupo(id);
    }

    @PutMapping("/modificar-cupo")
    public GrupoDTO modificarCuposGrupo(@RequestParam String grupoId,
                                        @RequestParam int cupo) {
        return grupoService.modificarCuposGrupo(grupoId, cupo);
    }

    @PutMapping("/agregar-estudiante")
    public GrupoDTO agregarEstudianteAGrupo(@RequestParam String grupoId,
                                            @RequestParam String estudianteId) {
        return grupoService.agregarEstudianteAGrupo(grupoId, estudianteId);
    }

    @DeleteMapping("/eliminar-estudiante")
    public GrupoDTO eliminarEstudianteAGrupo(@RequestParam String grupoId,
                                             @RequestParam String estudianteId) {
        return grupoService.eliminarEstudianteAGrupo(grupoId, estudianteId);
    }
}

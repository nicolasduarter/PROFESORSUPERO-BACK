package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping("/crear-Grupo")
    public ResponseEntity<GrupoDTO> crearGrupo(@RequestBody GrupoDTO dto) {
        return ResponseEntity.ok(grupoService.crearGrupo(dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarGrupo(@PathVariable String id) {
        grupoService.eliminarGrupo(id);
    }

    @PatchMapping("/modificar-Cupo")
    public ResponseEntity<GrupoDTO> modificarCuposGrupo(@RequestParam String grupoId,
                                     @RequestParam int cupo) {
        return ResponseEntity.ok(grupoService.modificarCuposGrupo(grupoId, cupo));
    }

    @PatchMapping("/{grupoId}/Estudiante/{profesorId}")
    public ResponseEntity<GrupoDTO> agregarEstudianteAGrupo(@PathVariable String grupoId,
                                         @PathVariable String estudianteId) {
        return ResponseEntity.ok(grupoService.agregarEstudianteAGrupo(grupoId, estudianteId));
    }

    @PatchMapping("/{grupoId}/Estudiante")
    public ResponseEntity<GrupoDTO> eliminarEstudianteAGrupo(@RequestParam String estudianteId, @PathVariable String grupoId) {
        return ResponseEntity.ok(grupoService.eliminarEstudianteAGrupo(grupoId, estudianteId));
    }


    @PatchMapping("/{grupoId}/profesor/{profesorId}")
    public ResponseEntity<GrupoDTO> agregarProfesorAGrupo(@PathVariable String grupoId,
                                                          @PathVariable String profesorId){
        return ResponseEntity.ok(grupoService.agregarProfesorAGrupo(grupoId, profesorId));
    }


    @PatchMapping("/{grupoId}/Profesor")
    public ResponseEntity<GrupoDTO> eliminarProfesorAGrupo(@PathVariable String grupoId){
        return ResponseEntity.ok(grupoService.eliminarProfesorAGrupo(grupoId));
    }


    @PatchMapping("/{grupoId}/clases")
    public ResponseEntity<GrupoDTO> agregarClasesAGrupo(  @PathVariable String grupoId,
                                                          @RequestBody List<ClaseDTO> clases){
        GrupoDTO grupoActualizado = grupoService.agregarClasesAGrupo(grupoId, clases);
        return ResponseEntity.ok(grupoActualizado);
    }

    @PatchMapping("/{grupoId}/clases-Eliminanacion")
    public ResponseEntity<GrupoDTO> eliiminarClasesAGrupo(  @PathVariable String grupoId){
        GrupoDTO grupoActualizado = grupoService.eliminarClasesAGrupo(grupoId);
        return ResponseEntity.ok(grupoActualizado);
    }

    @GetMapping("/{grupoId}/lista-espera")
    public ResponseEntity<List<Estudiante>> consultarListaEspera(@PathVariable String grupoId) {
        return ResponseEntity.ok(grupoService.consultarListaEspera(grupoId));
    }

    @DeleteMapping("/{grupoId}/lista-espera/{estudianteId}")
    public ResponseEntity<GrupoDTO> eliminarDeListaEspera(@PathVariable String grupoId,
                                                          @PathVariable String estudianteId) {
        return ResponseEntity.ok(grupoService.eliminarDeListaEspera(grupoId, estudianteId));
    }

    @PatchMapping("/{grupoID}/cupo/{cupos}")
    public ResponseEntity<GrupoDTO> modificarCuposMax(@PathVariable String grupoID,  @PathVariable int cupos){
        GrupoDTO grupoActualizado = grupoService.modificarCuposMAXGrupo(grupoID,cupos);
        return ResponseEntity.ok(grupoActualizado);
    }

    @PatchMapping("/{grupoID}/nombre/{nombre}")
    public ResponseEntity<GrupoDTO> cambiarNombreAGrupo(@PathVariable String grupoID,  @PathVariable String nombre){
        GrupoDTO grupoActualizado = grupoService.cambiarNombreAGrupo(grupoID,nombre);
        return ResponseEntity.ok(grupoActualizado);
    }


}



package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @PostMapping
    public ResponseEntity<GrupoDTO> crearGrupo(@RequestBody GrupoDTO dto) {
        return ResponseEntity.ok(grupoService.crearGrupo(dto));
    }

    @DeleteMapping("/{id}")
    public void eliminarGrupo(@PathVariable String id) {
        grupoService.eliminarGrupo(id);
    }

    @PatchMapping("/{grupoId}/cupo-maximo")
    public ResponseEntity<GrupoDTO> modificarCuposMax(@PathVariable String grupoId,
                                                      @RequestBody Map<String, Integer> body) {
        int cupos = body.get("cupos");
        GrupoDTO grupoActualizado = grupoService.updateMaximumCapacity(grupoId, cupos);
        return ResponseEntity.ok(grupoActualizado);
    }

    @PatchMapping("/{grupoId}/estudiantes/{estudianteId}")
    public ResponseEntity<GrupoDTO> agregarEstudianteAGrupo(@PathVariable String grupoId,
                                                            @PathVariable String estudianteId) {
        return ResponseEntity.ok(grupoService.agregarEstudianteAGrupo(grupoId, estudianteId));
    }



    @DeleteMapping("/{grupoId}/estudiantes/{estudianteId}")
    public ResponseEntity<GrupoDTO> eliminarEstudianteDeGrupo(@PathVariable String grupoId,
                                                              @PathVariable String estudianteId) {
        return ResponseEntity.ok(grupoService.deleteStudentOfGroup(grupoId, estudianteId));
    }


    @PatchMapping("/{grupoId}/profesor/{profesorId}")
    public ResponseEntity<GrupoDTO> agregarProfesorAGrupo(@PathVariable String grupoId,
                                                          @PathVariable String profesorId) {
        return ResponseEntity.ok(grupoService.agregarProfesorAGrupo(grupoId, profesorId));
    }


    @DeleteMapping("/{grupoId}/profesor")
    public ResponseEntity<GrupoDTO> eliminarProfesorDeGrupo(@PathVariable String grupoId) {
        return ResponseEntity.ok(grupoService.eliminarProfesorAGrupo(grupoId));
    }


    @PatchMapping("/{grupoId}/clases")
    public ResponseEntity<GrupoDTO> agregarClasesAGrupo(@PathVariable String grupoId,
                                                        @RequestBody List<ClaseDTO> clases) {
        GrupoDTO grupoActualizado = grupoService.agregarClasesAGrupo(grupoId, clases);
        return ResponseEntity.ok(grupoActualizado);
    }

    @DeleteMapping("/{grupoId}/clases")
    public ResponseEntity<GrupoDTO> eliminarClasesDeGrupo(@PathVariable String grupoId) {
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
        GrupoDTO grupoActualizado = grupoService.updateMaximumCapacity(grupoID,cupos);
        return ResponseEntity.ok(grupoActualizado);
    }

    @PatchMapping("/{grupoId}/nombre")
    public ResponseEntity<GrupoDTO> cambiarNombreAGrupo(@PathVariable String grupoId,
                                                        @RequestBody Map<String, String> body) {
        String nombre = body.get("nombre");
        GrupoDTO grupoActualizado = grupoService.cambiarNombreAGrupo(grupoId, nombre);
        return ResponseEntity.ok(grupoActualizado);
    }


    @GetMapping("/{grupoId}/capacidad")
    public ResponseEntity<GrupoDTO> getMaximumCapacity(@PathVariable String grupoId){
        GrupoDTO grupoActualizado = grupoService.getMaximumCapacity(grupoId);
        return ResponseEntity.ok(grupoActualizado);
    }

    @GetMapping("/{grupoId}/capacidad-actual")
    public ResponseEntity<GrupoDTO> getActualCapcity(@PathVariable String grupoId){
        GrupoDTO grupoActualizado = grupoService.getActualCapacity(grupoId);
        return ResponseEntity.ok(grupoActualizado);
    }

}



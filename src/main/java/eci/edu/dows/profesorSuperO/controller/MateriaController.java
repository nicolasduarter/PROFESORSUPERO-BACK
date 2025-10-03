package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @PostMapping("/crear")
    public ResponseEntity<MateriaDTO> crearMateria(@RequestBody MateriaDTO materiaDTO) {
        MateriaDTO creada = materiaService.crearMateria(materiaDTO);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MateriaDTO> obtenerMateriaPorID(@PathVariable String id) {
        MateriaDTO materia = materiaService.buscarPorId(id);
        return ResponseEntity.ok(materia);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<MateriaDTO>> obtenerMateriasPorNombre(@PathVariable String nombre) {
        List<MateriaDTO> materias = materiaService.findByNombre(nombre);
        return ResponseEntity.ok(materias);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> borrarMateria(@PathVariable String id) {
        materiaService.eliminarMateriaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/id/{id}/creditos")
    public ResponseEntity<MateriaDTO> actualizarCreditos(@PathVariable String id,
                                                         @RequestParam int creditos) {
        MateriaDTO actualizada = materiaService.actualizarCreditos(id, creditos);
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/id/{id}/nombre")
    public ResponseEntity<MateriaDTO> actualizarNombre(@PathVariable String id,
                                                       @RequestParam String nombre) {
        MateriaDTO actualizada = materiaService.actualizarNombre(id, nombre);
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}/prerequisitos")
    public ResponseEntity<MateriaDTO> agregarPrerequisitos(@PathVariable String id,
                                                           @RequestBody List<MateriaDTO> prerequisitos) {
        MateriaDTO actualizada = materiaService.agregarPrerequisitos(id, prerequisitos);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}/prerequisitos")
    public ResponseEntity<MateriaDTO> eliminarPrerequisitos(@PathVariable String id,
                                                            @RequestBody List<MateriaDTO> prerequisitos) {
        MateriaDTO actualizada = materiaService.eliminarPrerequisitos(id, prerequisitos);
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}/prerequisito")
    public ResponseEntity<MateriaDTO> agregarPrerequisito(@PathVariable String id,
                                                          @RequestBody MateriaDTO prerequisito) {
        MateriaDTO actualizada = materiaService.agregarPrerequisito(id, prerequisito);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}/prerequisito")
    public ResponseEntity<MateriaDTO> eliminarPrerequisito(@PathVariable String id,
                                                           @RequestBody MateriaDTO prerequisito) {
        MateriaDTO actualizada = materiaService.eliminarPrerequisito(id, prerequisito);
        return ResponseEntity.ok(actualizada);
    }
}



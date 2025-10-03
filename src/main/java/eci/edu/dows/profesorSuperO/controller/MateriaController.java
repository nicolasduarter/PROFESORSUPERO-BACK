package eci.edu.dows.profesorSuperO.controller;


import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @PostMapping
    public MateriaDTO crearMateria(@RequestBody Materia materia) {
       return materiaService.guardarMateria(materia);
    }

    @DeleteMapping
    public void eliminarMateria(@RequestBody Materia materia) {
        materiaService.eliminarMateria(materia);
    }

    @GetMapping("/id/{id}")
    public Optional<Materia> obtenerMateriaPorID(@PathVariable String id) {
        return materiaService.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Materia> obtenerMateriasPorNombre(@PathVariable String nombre) {
        return materiaService.findByNombre(nombre);
    }

    @DeleteMapping("/id/{id}")
    public void borrarMateria(@PathVariable String id) {
        materiaService.eliminarMateriaPorId(id);
    }

    @PutMapping("/materias")
    public void actualizarMateria(@RequestBody Materia materia) {
        materiaService.guardarMateria(materia);
    }

    @GetMapping("/estado")
    public List<Materia> obtenerMateriasPorEstado(@RequestParam String estado) {
        return materiaService.buscarPorEstado(estado);
    }

    @PatchMapping("/id/{id}/creditos")
    public void actualizarCreditos(
            @PathVariable String id,
            @RequestParam int creditos) {
        try {
            materiaService.actualizarCreditos(id, creditos);
        } catch (RuntimeException ignored) {
        }
    }


}

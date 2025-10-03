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
    public MateriaDTO crearMateria(@RequestBody MateriaDTO materia) {
       return materiaService.crearMateria(materia);
    }


    @GetMapping("/id/{id}")
    public MateriaDTO obtenerMateriaPorID(@PathVariable String id) {
        return materiaService.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<MateriaDTO> obtenerMateriasPorNombre(@PathVariable String nombre) {
        return materiaService.findByNombre(nombre);
    }

    @DeleteMapping("/id/{id}")
    public void borrarMateria(@PathVariable String id) {
        materiaService.eliminarMateriaPorId(id);
    }

    @PutMapping("/materias")
    public void actualizarMateria(@RequestBody MateriaDTO materia) {
        materiaService.crearMateria(materia);
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

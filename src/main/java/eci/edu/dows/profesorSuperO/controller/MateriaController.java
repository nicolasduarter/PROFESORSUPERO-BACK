package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    private final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping("/crear")
    public MateriaDTO crearMateria(@RequestBody MateriaDTO materiaDTO) {
        return materiaService.crearMateria(materiaDTO);
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

    @PutMapping("/id/{id}/creditos")
    public MateriaDTO actualizarCreditos(@PathVariable String id,
                                         @RequestParam int creditos) {
        return materiaService.actualizarCreditos(id, creditos);
    }

    @PutMapping("/id/{id}/nombre")
    public MateriaDTO actualizarNombre(@PathVariable String id,
                                       @RequestParam String nombre) {
        return materiaService.actualizarNombre(id, nombre);
    }

    @PutMapping("/{id}/prerequisitos")
    public MateriaDTO agregarPrerequisitos(@PathVariable String id,
                                           @RequestBody List<MateriaDTO> prerequisitos) {
        return materiaService.agregarPrerequisitos(id, prerequisitos);
    }

    @DeleteMapping("/{id}/prerequisitos")
    public MateriaDTO eliminarPrerequisitos(@PathVariable String id,
                                            @RequestBody List<MateriaDTO> prerequisitos) {
        return materiaService.eliminarPrerequisitos(id, prerequisitos);
    }

    @PutMapping("/{id}/prerequisito")
    public MateriaDTO agregarPrerequisito(@PathVariable String id,
                                          @RequestBody MateriaDTO prerequisito) {
        return materiaService.agregarPrerequisito(id, prerequisito);
    }

    @DeleteMapping("/{id}/prerequisito")
    public MateriaDTO eliminarPrerequisito(@PathVariable String id,
                                           @RequestBody MateriaDTO prerequisito) {
        return materiaService.eliminarPrerequisito(id, prerequisito);
    }
}

package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;

    @Autowired
    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @PostMapping("/crear")
    public ClaseDTO crearClase(@RequestBody ClaseDTO dto) {
        return claseService.crearClase(dto);
    }

    @GetMapping("/clases/{id}")
    public ClaseDTO buscarClasePorId(@PathVariable String id) {
        return claseService.buscarClasePorId(id);
    }


    @PutMapping("/modificarHoraInicio")
    public ClaseDTO modificarHoraInicio(@RequestParam String claseId,
                                        @RequestParam String inicio) {
        LocalTime horaInicio = LocalTime.parse(inicio);
        return claseService.modificarHoraInicio(claseId, horaInicio);
    }

    @PutMapping("/modificarHoraFin")
    public ClaseDTO modificarHoraFin(@RequestParam String claseId,
                                     @RequestParam String fin) {
        LocalTime horaFin = LocalTime.parse(fin);
        return claseService.modificarHoraFin(claseId, horaFin);
    }

    @PutMapping("/modificarSalon")
    public ClaseDTO modificarSalonClase(@RequestParam String claseId,
                                        @RequestParam String salon) {
        return claseService.modificarSalonClase(claseId, salon);
    }

    @DeleteMapping("/{id}/clase")
    public void eliminarClase(@PathVariable String id) {
        claseService.eliminarClase(id);
    }

}

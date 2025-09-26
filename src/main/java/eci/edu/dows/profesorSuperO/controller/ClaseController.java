package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.service.ClaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clases")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @PostMapping("/crear")
    public Clase crearClase(@RequestBody ClaseDTO dto) {
        return claseService.crearClase(dto);
    }

    @PutMapping("/modificarHorario")
    public Clase modificarHorarioClase(@RequestParam String claseId,
                                       @RequestParam String horario) {
        return claseService.modificarHorarioClase(claseId, horario);
    }

    @PutMapping("/modificarSalon")
    public Clase modificarSalonClase(@RequestParam String claseId,
                                     @RequestParam String salon) {
        return claseService.modificarSalonClase(claseId, salon);
    }

    @PutMapping("/agregarEstudiante")
    public Clase agregarEstudianteAClase(@RequestParam String claseId,
                                         @RequestParam String estudianteId) {
        return claseService.agregarEstudianteAClase(claseId, estudianteId);
    }

    @PutMapping("/eliminarEstudiante")
    public Clase eliminarEstudianteDeClase(@RequestParam String claseId,
                                           @RequestParam String estudianteId) {
        return claseService.eliminarEstudianteDeClase(claseId, estudianteId);
    }
}


package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
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
    public Clase crearClase(@RequestParam String idClase,
                            @RequestParam String franjaHoraria,
                            @RequestBody Profesor profesor,
                            @RequestParam String salon,
                            @RequestParam String estado) {
        return claseService.crearClase(idClase, franjaHoraria, profesor, salon, estado);
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


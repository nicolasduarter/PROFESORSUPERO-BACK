package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.ClaseDTO;
import eci.edu.dows.profesorSuperO.service.Interfaces.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Map;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;

    @Autowired
    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }




    @PostMapping
    public ClaseDTO crearClase(@RequestBody ClaseDTO dto) {
        return claseService.crearClase(dto);
    }

    @GetMapping("/{id}")
    public ClaseDTO buscarClasePorId(@PathVariable String id) {
        return claseService.buscarClasePorId(id);
    }

    @PutMapping("/{id}")
    public ClaseDTO updateClass(@PathVariable String id, @RequestBody ClaseDTO dto) {
        dto.setIdClase(id);
        return claseService.updateClass(dto);
    }


    @PatchMapping("/{id}/hora-inicio")
    public ClaseDTO modificarHoraInicio(@PathVariable String id,
                                        @RequestBody Map<String, String> body) {
        LocalTime horaInicio = LocalTime.parse(body.get("horaInicio"));
        return claseService.modificarHoraInicio(id, horaInicio);
    }

    @PatchMapping("/{id}/hora-fin")
    public ClaseDTO modificarHoraFin(@PathVariable String id,
                                     @RequestBody Map<String, String> body) {
        LocalTime horaFin = LocalTime.parse(body.get("horaFin"));
        return claseService.modificarHoraFin(id, horaFin);
    }

    @PatchMapping("/{id}/salon")
    public ClaseDTO modificarSalonClase(@PathVariable String id,
                                        @RequestBody Map<String, String> body) {
        String salon = body.get("salon");
        return claseService.modificarSalonClase(id, salon);
    }

    @PatchMapping("/{id}/dia-semana")
    public ClaseDTO updateDayOfWeekClase(@PathVariable String id,
                                         @RequestBody Map<String, String> body) {
        String diaSemana = body.get("diaSemana");
        return claseService.updateDayOfWeekClase(id, diaSemana);
    }


    @DeleteMapping("/{id}")
    public void eliminarClase(@PathVariable String id) {
        claseService.eliminarClase(id);
    }

}

package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.service.ConsultasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultasController {

    private final ConsultasService consultasService;

    public ConsultasController(ConsultasService consultasService) {
        this.consultasService = consultasService;
    }

    @GetMapping("/estudiante/{estudianteId}/ultimoHorario")
    public Horario consultarUltimoHorarioEstudiante(@PathVariable String estudianteId) {
        return consultasService.consultarUltimoHorarioEstudiante(estudianteId);
    }

    @GetMapping("/estudiante/{estudianteId}/horarios")
    public List<Horario> consultarHorariosEstudiante(@PathVariable String estudianteId) {
        return consultasService.consultarHorariosEstudiante(estudianteId);
    }

    @GetMapping("/estudiante/{estudianteId}/solicitudes")
    public List<Solicitud> consultarSolicitudesEstudiante(@PathVariable String estudianteId) {
        return consultasService.consultarSolicitudesEstudiante(estudianteId);
    }

    @GetMapping("/estudiante/{estudianteId}/semaforo")
    public Semaforo consultarSemaforoEstudiante(@PathVariable String estudianteId) {
        return consultasService.consultarSemaforoEstudiante(estudianteId);
    }

    @GetMapping("/grupo/{grupoId}/profesor")
    public Profesor consultarProfesorGrupo(@PathVariable String grupoId) {
        return consultasService.consultarProfesorGrupo(grupoId);
    }

    @GetMapping("/grupo/{grupoId}/cupos")
    public int consultarCuposGrupo(@PathVariable String grupoId) {
        return consultasService.consultarCuposGrupo(grupoId);
    }

    @GetMapping("/grupo/{grupoId}")
    public Grupo obtenerGrupo(@PathVariable String grupoId) {
        return consultasService.obtenerGrupo(grupoId);
    }
}


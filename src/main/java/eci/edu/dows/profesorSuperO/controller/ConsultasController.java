package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.HorarioDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.service.ConsultasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
public class ConsultasController {

    private final ConsultasService consultasService;

    public ConsultasController(ConsultasService consultasService) {
        this.consultasService = consultasService;
    }

    @GetMapping("/estudiante/{estudianteId}/ultimoHorario")
    public ResponseEntity<HorarioDTO> consultarUltimoHorarioEstudiante(@PathVariable String estudianteId) {
        var horario = consultasService.consultarUltimoHorarioEstudiante(estudianteId);
        return horario != null
                ? ResponseEntity.ok(consultasService.consultarHorariosEstudiante(estudianteId)
                .stream().reduce((first, second) -> second).orElse(null))
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/estudiante/{estudianteId}/horarios")
    public ResponseEntity<List<HorarioDTO>> consultarHorariosEstudiante(@PathVariable String estudianteId) {
        List<HorarioDTO> horarios = consultasService.consultarHorariosEstudiante(estudianteId);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/estudiante/{estudianteId}/solicitudes")
    public ResponseEntity<List<SolicitudDTO>> consultarSolicitudesEstudiante(@PathVariable String estudianteId) {
        List<SolicitudDTO> solicitudes = consultasService.consultarSolicitudesEstudiante(estudianteId);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/estudiante/{estudianteId}/semaforo")
    public ResponseEntity<SemaforoDTO> consultarSemaforoEstudiante(@PathVariable String estudianteId) {
        SemaforoDTO semaforo = consultasService.consultarSemaforoEstudiante(estudianteId);
        return ResponseEntity.ok(semaforo);
    }

    @GetMapping("/grupo/{grupoId}/profesor")
    public ResponseEntity<ProfesorDTO> consultarProfesorGrupo(@PathVariable String grupoId) {
        Optional<ProfesorDTO> profesor = consultasService.consultarProfesorGrupo(grupoId);
        return profesor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/grupo/{grupoId}/cupos")
    public ResponseEntity<Integer> consultarCuposGrupo(@PathVariable String grupoId) {
        int cupos = consultasService.consultarCuposGrupo(grupoId);
        return ResponseEntity.ok(cupos);
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<GrupoDTO> obtenerGrupo(@PathVariable String grupoId) {
        GrupoDTO grupo = consultasService.obtenerGrupo(grupoId);
        return ResponseEntity.ok(grupo);
    }

}

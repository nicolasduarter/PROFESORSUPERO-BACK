package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.HorarioDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final SolicitudService solicitudService;
    private final EstudianteService estudianteService;
    private final ConsultasService consultasService;

    @Autowired
    public EstudianteController(SolicitudService solicitudService, EstudianteService estudianteService,ConsultasService consultasService) {
        this.solicitudService = solicitudService;
        this.estudianteService = estudianteService;
        this.consultasService = consultasService;
    }

    /**
     * CRUD
     */
    @PostMapping
    public ResponseEntity<EstudianteDTO> createStudent(@RequestBody EstudianteDTO dto) {
        return ResponseEntity.ok(estudianteService.createStudent(dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(estudianteService.getStudentById(id));

    }


    @GetMapping("/email/{email}")
    public ResponseEntity<EstudianteDTO> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(estudianteService.getStudentByEmail(email));
    }


    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstudianteDTO> getStudentByFullName(@PathVariable String nombre) {
        return ResponseEntity.ok(estudianteService.getStudentByFullName(nombre));
    }


    @GetMapping("/facultad/{idFacultad}")
    public ResponseEntity<List<EstudianteDTO>> getStudentsByFaculty(@PathVariable String idFacultad) {
        return ResponseEntity.ok(estudianteService.getStudentByFaculty(idFacultad));
    }


    @GetMapping("/semestre/{semestre}")
    public ResponseEntity<List<EstudianteDTO>> getStudentsBySemester(@PathVariable int semestre) {
        return ResponseEntity.ok(estudianteService.getStudentBySemester(semestre));
    }


    @PutMapping
    public ResponseEntity<EstudianteDTO> updateStudent(@RequestBody EstudianteDTO dto) {
        return ResponseEntity.ok(estudianteService.updateStudent(dto));
    }


    @PatchMapping("/{id}/correo")
    public ResponseEntity<EstudianteDTO> updateStudentEmail(@PathVariable String id, @RequestBody String nuevoCorreo) {
        return ResponseEntity.ok(estudianteService.updateStudentByMail(id, nuevoCorreo));
    }


    @PatchMapping("/{id}/semestre")
    public ResponseEntity<EstudianteDTO> updateStudentSemester(@PathVariable String id, @RequestBody int semestre) {
        return ResponseEntity.ok(estudianteService.updateStudentBySemester(id, semestre));
    }


    @PatchMapping("/{id}/facultad/{idFacultad}")
    public ResponseEntity<EstudianteDTO> updateStudentFaculty(@PathVariable String id, @PathVariable String idFacultad) {
        return ResponseEntity.ok(estudianteService.updateStudentByFaculty(id, idFacultad));
    }


    @PatchMapping("/{id}/nombre")
    public ResponseEntity<EstudianteDTO> updateStudentName(@PathVariable String id, @RequestBody String nombre) {
        return ResponseEntity.ok(estudianteService.updateStudentByFullName(id,nombre));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        estudianteService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Consultas
     */

    @GetMapping("/{idEstudiante}/horario/actual")
    public ResponseEntity<HorarioDTO> getCurrentSchedule(@PathVariable String idEstudiante) {
        return ResponseEntity.ok(consultasService.consultarUltimoHorarioEstudiante(idEstudiante));
    }

    @GetMapping("/{idEstudiante}/horario/historial")
    public ResponseEntity<List<HorarioDTO>> getAllSchedules(@PathVariable String idEstudiante) {
        return ResponseEntity.ok(consultasService.consultarHorariosEstudiante(idEstudiante));
    }

    @GetMapping("/{idEstudiante}/semaforo")
    public ResponseEntity<SemaforoDTO> getAcademicTrafficLight(@PathVariable String idEstudiante) {
        return ResponseEntity.ok(consultasService.consultarSemaforoEstudiante(idEstudiante));
    }

    @GetMapping("/{idEstudiante}/solicitudes/{idSolicitud}/estado")
    public ResponseEntity<SolicitudDTO> getRequestStatus(@PathVariable String idEstudiante,
            @PathVariable String idSolicitud) {
        return ResponseEntity.ok(consultasService.consultarSolicitudPorEstudiante(idEstudiante,idSolicitud));

    }

    @GetMapping("/{idEstudiante}/solicitudes")
    public ResponseEntity<List<SolicitudDTO>> getRequestHistory(@PathVariable String idEstudiante) {
        return ResponseEntity.ok(consultasService.consultarSolicitudesEstudiante(idEstudiante));
    }

    /**
     * Solicitudes
     */


    @PostMapping("/solicitudes/cambio-materia")
    public ResponseEntity<SolicitudCambioMateriaDTO> crearSolicitudCambioMateria(@Valid @RequestBody SolicitudCambioMateriaDTO dto) {

        return ResponseEntity.ok(solicitudService.crearSolicitudCambioMateria(dto));
    }

    @PostMapping("/solicitudes/cambio-grupo")
    public ResponseEntity<SolicitudCambioGrupoDTO> crearSolicitudCambioGrupo(@Valid @RequestBody SolicitudCambioGrupoDTO dto) {
        return ResponseEntity.ok(solicitudService.crearSolicitudCambioGrupo(dto));
    }





}

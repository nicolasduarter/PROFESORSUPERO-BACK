package eci.edu.dows.profesorSuperO.controller.Usuarios;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.CalendarioAcademicoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.DecanaturaDTO;
import eci.edu.dows.profesorSuperO.service.Implementaciones.DecanaturaServiceImpl;
import eci.edu.dows.profesorSuperO.service.Implementaciones.DecanoServiceImpl;
import eci.edu.dows.profesorSuperO.service.Interfaces.DecanaturaService;
import eci.edu.dows.profesorSuperO.service.Interfaces.DecanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.EstudianteDTO;


import java.util.List;
import java.util.Map;


/**
 Controlador de Decanatura
 Permite obtener solicitudes de su facultadName
 Permite cambiar el estado de una solicitud
 Solo para el rol de decanatura
 */
@RestController
@RequestMapping("/decanatura")
public class DecanaturaController {

    private final DecanaturaService decanaturaService;
    private final DecanoService decanoService;

    @Autowired
    public DecanaturaController(DecanaturaServiceImpl decanaturaService, DecanoServiceImpl decanoService) {
        this.decanaturaService = decanaturaService;
        this.decanoService = decanoService;
    }

    /**
     * Crud
     */

    @PostMapping
    public ResponseEntity<DecanaturaDTO> crearDecano(@RequestBody DecanaturaDTO dto) {
        return ResponseEntity.ok(decanoService.createDean(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DecanaturaDTO> getDecanoPorId(@PathVariable String id) {
        return ResponseEntity.ok(decanoService.getDeanById(id));
    }

    @GetMapping("/correo")
    public ResponseEntity<DecanaturaDTO> getDecanoPorCorreo(@RequestParam String email) {
        return ResponseEntity.ok(decanoService.getDeanByEmail(email));
    }

    @GetMapping("/nombre")
    public ResponseEntity<DecanaturaDTO> getDecanoPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(decanoService.getDeanByFullName(nombre));
    }

    @GetMapping("/facultad/{facultadId}")
    public ResponseEntity<DecanaturaDTO> getDecanoPorFacultad(@PathVariable String facultadId) {
        return ResponseEntity.ok(decanoService.getDeanByFaculty(facultadId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DecanaturaDTO> actualizarDecano(@PathVariable String id,
                                                          @RequestBody DecanaturaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(decanoService.updateDean(dto));
    }

    @PatchMapping("/{id}/correo")
    public ResponseEntity<DecanaturaDTO> actualizarCorreo(@PathVariable String id,
                                                          @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(decanoService.updateDeanByMail(id, body.get("correo")));
    }

    @PatchMapping("/{id}/nombre")
    public ResponseEntity<DecanaturaDTO> actualizarNombre(@PathVariable String id,
                                                          @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(decanoService.updateDeanByFullName(id, body.get("nombre")));
    }

    @PatchMapping("/{id}/facultad")
    public ResponseEntity<DecanaturaDTO> actualizarFacultad(@PathVariable String id,
                                                            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(decanoService.updateDeanByFaculty(id, body.get("facultadId")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDecano(@PathVariable String id) {
        decanoService.deleteDean(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/solicitudes/facultad/{facultadId}")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPorFacultad(@PathVariable String facultadId) {
        FacultadDTO facultadDTO = new FacultadDTO();
        facultadDTO.setId(facultadId);
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPorFacultad(facultadDTO));
    }


    @GetMapping("/solicitudes/prioridad/{prioridad}")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPorPrioridad(@PathVariable int prioridad) {
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPorPrioridad(prioridad));
    }

    @GetMapping("/facultades/{facultadId}/solicitudes/pendientes")
    public ResponseEntity<List<SolicitudDTO>> getSolicitudesPendientes(@PathVariable String facultadId) {
        FacultadDTO facultadDTO = new FacultadDTO();
        facultadDTO.setId(facultadId);
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPendientes(facultadDTO));
    }

    @GetMapping("/facultades/{facultadId}/solicitudes")
    public  ResponseEntity<List<SolicitudDTO>> getSolicitudesPorFacultadyOrden(@PathVariable String facultadId) {
        return ResponseEntity.ok(decanaturaService.obtenerSolicitudesPorOrdenFacultad(facultadId));
    }

    @PatchMapping("/solicitudes/{solicitudId}/estado")
    public ResponseEntity<SolicitudDTO> cambiarEstadoSolicitud(@PathVariable String solicitudId,
                                                               @RequestBody String estado) {
        return ResponseEntity.ok(decanaturaService.cambiarEstado(solicitudId, estado));
    }

    @GetMapping("/estudiantes/{estudianteId}")
    public ResponseEntity<EstudianteDTO> getEstudiante(@PathVariable String estudianteId) {
        return ResponseEntity.ok(decanaturaService.verInformacionEstudiante(estudianteId));
    }


    @PostMapping("/periodos")
    public ResponseEntity<CalendarioAcademicoDTO> crearCalendario(@RequestBody CalendarioAcademicoDTO calendarioAcademicoDTO) {
        return ResponseEntity.ok(decanaturaService.createAcademicCalendar(calendarioAcademicoDTO));
    }

    @PatchMapping("/periodos/{id}/inicio")
    public ResponseEntity<CalendarioAcademicoDTO> actualizarInicio(@PathVariable String id,
                                                                   @RequestBody Map<String, String> inicio) {
        String startDay = inicio.get("startDay");
        return ResponseEntity.ok(decanaturaService.updateStartDay(startDay, id));
    }

    @PatchMapping("/periodos/{id}/fin")
    public ResponseEntity<CalendarioAcademicoDTO> actualizarFin(@PathVariable String id,
                                                                @RequestBody Map<String, String> fin) {
        String finalDay = fin.get("finalDay");
        return ResponseEntity.ok(decanaturaService.updateFinalDay(finalDay, id));
    }
}


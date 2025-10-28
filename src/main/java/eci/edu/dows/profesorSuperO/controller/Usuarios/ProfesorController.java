package eci.edu.dows.profesorSuperO.controller.Usuarios;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.service.Implementaciones.ProfesorServiceImpl;
import eci.edu.dows.profesorSuperO.service.Interfaces.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorServiceImpl profesorService) {
        this.profesorService = profesorService;
    }

    // Crear profesor
    @PostMapping
    public ResponseEntity<ProfesorDTO> createTeacher(@RequestBody ProfesorDTO dto) {
        return ResponseEntity.ok(profesorService.createTeacher(dto));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getTeacherById(@PathVariable String id) {
        return ResponseEntity.ok(profesorService.getTeacherById(id));
    }

    // Obtener por correo
    @GetMapping("/correo")
    public ResponseEntity<ProfesorDTO> getTeacherByEmail(@RequestParam String email) {
        return ResponseEntity.ok(profesorService.getTeacherByEmail(email));
    }

    // Obtener por nombre completo
    @GetMapping("/nombre")
    public ResponseEntity<ProfesorDTO> getTeacherByFullName(@RequestParam String nombre) {
        return ResponseEntity.ok(profesorService.getTeacherByFullName(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> updateTeacher(@PathVariable String id, @RequestBody ProfesorDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(profesorService.updateTeacher(dto));
    }

    @PatchMapping("/{id}/nombre")
    public ResponseEntity<ProfesorDTO> updateTeacherName(@PathVariable String id,
                                                         @RequestBody Map<String, String> body) {
        String nombre = body.get("nombre");
        return ResponseEntity.ok(profesorService.updateTeacherByFullName(id, nombre));
    }

    @PatchMapping("/{id}/correo")
    public ResponseEntity<ProfesorDTO> updateTeacherEmail(@PathVariable String id,
                                                          @RequestBody Map<String, String> body) {
        String correo = body.get("correo");
        return ResponseEntity.ok(profesorService.updateTeacherByMail(id, correo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        profesorService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
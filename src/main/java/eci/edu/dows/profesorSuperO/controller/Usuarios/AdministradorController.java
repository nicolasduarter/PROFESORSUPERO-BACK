package eci.edu.dows.profesorSuperO.controller.Usuarios;


import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.AdminDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.newRolUserDTO;
import eci.edu.dows.profesorSuperO.service.AdministradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/administracion")
public class AdministradorController {

    private final AdministradorService administradorService;

    public  AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }


    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        return  ResponseEntity.ok(administradorService.newAdmin(adminDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        try {
            administradorService.deleteAdmin(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<newRolUserDTO> updateRolUser(@PathVariable String id, @RequestParam String permiso) {
        newRolUserDTO dto = administradorService.updateRolUser(id, permiso);
        return ResponseEntity.ok(dto);
    }



}

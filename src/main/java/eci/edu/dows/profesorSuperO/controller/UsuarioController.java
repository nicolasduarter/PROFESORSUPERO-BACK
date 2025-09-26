package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.ProfesorDTO;
import eci.edu.dows.profesorSuperO.model.Usuario;
import eci.edu.dows.profesorSuperO.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/estudiantes")
    public Usuario crearEstudiante(@RequestBody EstudianteDTO dto) {

       return  usuarioService.crearEstudiante(dto);
    }

    @PostMapping("/profesores")
    public Usuario crearProfesor(@RequestBody ProfesorDTO dto) {
        return usuarioService.crearProfesor(dto);
    }

    @GetMapping("/verificar-usuario")
    public boolean verificarUsuario(@RequestParam String id,
                                    @RequestParam String correo,
                                    @RequestParam String clave) {
        return usuarioService.verificarUsuario(id, correo, clave);
    }

    @DeleteMapping
    public void eliminarUsuario(@RequestParam String id) {
        usuarioService.eliminarUsuario(id);
    }

    @PatchMapping("/usuario/{user}/clave/{contra}")
    public void actualizarClave(@PathVariable String user, @PathVariable String contra) {
        usuarioService.modificarClave(user, contra);
    }



}

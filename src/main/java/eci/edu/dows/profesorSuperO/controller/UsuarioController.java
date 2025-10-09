package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
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
    public EstudianteDTO crearEstudiante(@RequestBody EstudianteDTO dto) {
       return  usuarioService.crearEstudiante(dto);
    }

    @PostMapping("/profesores")
    public ProfesorDTO crearProfesor(@RequestBody ProfesorDTO dto) {
            return usuarioService.crearProfesor(dto);
    }



    @DeleteMapping
    public void eliminarUsuario(@RequestParam String id) {
        usuarioService.eliminarUsuario(id);
    }




}

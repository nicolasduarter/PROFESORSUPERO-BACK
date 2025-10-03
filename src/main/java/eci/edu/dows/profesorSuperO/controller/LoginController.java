package eci.edu.dows.profesorSuperO.controller;


import eci.edu.dows.profesorSuperO.Util.EstudianteMapper;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.LoginUsuario;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.LoginUsuariosFactory;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.UsuarioRegistroMapper;
import eci.edu.dows.profesorSuperO.model.Credencial;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.LoginRequestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Usuario;
import eci.edu.dows.profesorSuperO.repository.CredencialRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import eci.edu.dows.profesorSuperO.service.AutenticacionService;
import eci.edu.dows.profesorSuperO.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class LoginController {
    private final CredencialRepository credencialRepository;
    private final UsuarioRepository usuarioRepository;
    private final LoginUsuariosFactory  loginUsuariosFactory;
    private final AutenticacionService autenticacionService;

    public LoginController(CredencialRepository credencialRepository, UsuarioRepository usuarioRepository,
                           LoginUsuariosFactory loginUsuariosFactory, AutenticacionService  autenticacionService) {
        this.credencialRepository = credencialRepository;
        this.usuarioRepository = usuarioRepository;
        this.loginUsuariosFactory = loginUsuariosFactory;
        this.autenticacionService = autenticacionService;

    }

    @PostMapping("/Autenticacion")
    public ResponseEntity<?> autenticacion(@RequestBody LoginRequestDTO loginDTO) {
        UsuarioLoginDTO user = autenticacionService.autenticar(loginDTO);
        return ResponseEntity.ok(user);
    }



    @PostMapping("/Registro")
    public ResponseEntity<?> registro(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        Object response = autenticacionService.registrarUsuario(usuarioRegistroDTO);

        return ResponseEntity.ok(response);


    }




}

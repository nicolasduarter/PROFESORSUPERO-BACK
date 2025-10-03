package eci.edu.dows.profesorSuperO.controller;


import eci.edu.dows.profesorSuperO.Util.EstudianteMapper;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.LoginUsuario;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.LoginUsuariosFactory;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.UsuarioRegistroMapper;
import eci.edu.dows.profesorSuperO.model.Credencial;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.LoginRequestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Usuario;
import eci.edu.dows.profesorSuperO.repository.CredencialRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
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
    private final UsuarioRegistroMapper usuarioRegistroMapper;
    private final UsuarioService usuarioService;
    private final EstudianteMapper estudianteMapper;

    public LoginController(CredencialRepository credencialRepository, UsuarioRepository usuarioRepository,
                           LoginUsuariosFactory loginUsuariosFactory, UsuarioService usuarioService,
                           UsuarioRegistroMapper usuarioRegistroMapper,EstudianteMapper estudianteMapper) {
        this.credencialRepository = credencialRepository;
        this.usuarioRepository = usuarioRepository;
        this.loginUsuariosFactory = loginUsuariosFactory;
        this.usuarioRegistroMapper = usuarioRegistroMapper;
        this.usuarioService = usuarioService;
        this.estudianteMapper = estudianteMapper;
    }

    @PostMapping("/Autenticacion")
    public ResponseEntity<?> autenticacion(@RequestBody LoginRequestDTO loginDTO) {
        Credencial credencial = credencialRepository.findByUsuario(loginDTO.getUsuario()).
                orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        if (!credencial.getConstra().equals(loginDTO.getContra())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contra incorrecta");
        }

        Usuario usuario = usuarioRepository.findById(credencial.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario  no encontrado"));

        LoginUsuario estrategia = loginUsuariosFactory.obtenerEstrategia(usuario.getPermiso().toString());

        Object user = estrategia.crearUsuario(usuario);

        return ResponseEntity.ok(user);

    }



    //recordatorio hacer factory o usar el login factory oara el refristo
    @PostMapping("/Registro")
    public ResponseEntity<?> registro(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        if (credencialRepository.findByUsuario(usuarioRegistroDTO.getUsuario()).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe una cuenta con ese usuario");
        }

        EstudianteDTO estudianteDTO = usuarioRegistroMapper.usuarioRegistroDTOToEstudianteDTO(usuarioRegistroDTO);

        Estudiante t = estudianteMapper.toEstudiante(estudianteDTO);


        Credencial cred = new Credencial();
        cred.setUsuario(usuarioRegistroDTO.getUsuario());
        cred.setConstra(usuarioRegistroDTO.getContra());
        cred.setUsuarioId(t.getId());

        credencialRepository.save(cred);

        return ResponseEntity.ok("Usuario registrado");







    }




}

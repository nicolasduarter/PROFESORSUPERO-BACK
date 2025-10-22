package eci.edu.dows.profesorSuperO.controller;


import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.LoginRequestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.service.Implementaciones.AutenticacionServiceImpl;
import eci.edu.dows.profesorSuperO.service.Interfaces.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class LoginController {

    private final AutenticacionService autenticacionService;


    @Autowired
    public LoginController(AutenticacionService autenticacionService) {

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

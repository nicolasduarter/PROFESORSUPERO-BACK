package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.LoginRequestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroSalidaDTO;

public interface AutenticacionService {
    UsuarioLoginDTO autenticar(LoginRequestDTO loginDTO);

    UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO);
}

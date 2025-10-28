package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;

public interface LoginUsuario {
    UsuarioLoginDTO loginUsuario(Usuario usuario);
    UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO);

}

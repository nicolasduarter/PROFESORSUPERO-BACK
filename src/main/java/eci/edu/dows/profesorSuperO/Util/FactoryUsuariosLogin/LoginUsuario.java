package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.Usuario;

public interface LoginUsuario {
    UsuarioLoginDTO loginUsuario(Usuario usuario);
    UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO);

}

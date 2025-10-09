package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.Decanatura;
import eci.edu.dows.profesorSuperO.model.Usuario;

public class LoginDecanatura  implements LoginUsuario{
    @Override
    public UsuarioLoginDTO loginUsuario(Usuario usuario) {
        Decanatura e = (Decanatura) usuario;
        UsuarioLoginDTO registroU = new UsuarioLoginDTO();
        registroU.setUsuario(e.getFullName());
        registroU.setRol(e.getPermiso().toString());

        return registroU;
    }

    @Override
    public UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        return null;
    }
}

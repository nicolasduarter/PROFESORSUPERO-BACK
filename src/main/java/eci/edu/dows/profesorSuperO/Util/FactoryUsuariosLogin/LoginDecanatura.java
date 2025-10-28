package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.Usuarios.Decanatura;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LoginDecanatura  implements LoginUsuario{
    @Override
    public UsuarioLoginDTO loginUsuario(Usuario usuario) {
        Decanatura e = (Decanatura) usuario;
        UsuarioLoginDTO registroU = new UsuarioLoginDTO();
        registroU.setUsuario(e.getFullName());
        registroU.setRol(e.getPermiso().toString());
        registroU.setId(e.getId());

        if (e.getFacultad() != null) {
            registroU.setFacultad(e.getFacultad());
        }

        return registroU;
    }

    @Override
    public UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        return null;
    }
}

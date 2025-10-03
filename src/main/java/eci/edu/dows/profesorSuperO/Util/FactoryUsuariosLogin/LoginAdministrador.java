package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.Administrador;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Usuario;

public class LoginAdministrador  implements LoginUsuario{
    @Override
    public Object crearUsuario(Usuario usuario) {
        Administrador e = (Administrador) usuario;
        return e;
    }
}

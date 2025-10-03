package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.Decanatura;
import eci.edu.dows.profesorSuperO.model.Usuario;

public class LoginDecanatura  implements LoginUsuario{
    @Override
    public Object crearUsuario(Usuario usuario) {
        Decanatura e = (Decanatura) usuario;
        return e;
    }
}

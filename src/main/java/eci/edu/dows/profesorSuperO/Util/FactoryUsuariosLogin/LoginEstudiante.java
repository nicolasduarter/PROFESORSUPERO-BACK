package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;


import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Usuario;

public class LoginEstudiante  implements LoginUsuario {
    @Override
    public Object crearUsuario(Usuario usuario) {
        Estudiante e = (Estudiante) usuario;
        return e;
    }
}

package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LoginUsuariosFactory {
    private final Map<String, LoginUsuario> estrategias = new HashMap<>();

    public LoginUsuariosFactory(List<LoginUsuario> estrategiasDisponibles) {
        for (LoginUsuario estrategia : estrategiasDisponibles) {
            if (estrategia instanceof LoginEstudiante) {
                estrategias.put(Permisos.ESTUDIANTE.toString(), estrategia);
            } else if (estrategia instanceof LoginDecanatura) {
                estrategias.put(Permisos.DECANATURA.toString(), estrategia);
            } else if (estrategia instanceof  LoginAdministrador) {
                estrategias.put(Permisos.ADMINNISTRADOR.toString(), estrategia);
            }
        }
    }

    public LoginUsuario obtenerEstrategia(String rol) {
        LoginUsuario estrategia = estrategias.get(rol);
        if (estrategia == null) {
            throw new RuntimeException("Rol encontrado");
        }
        return estrategia;
    }


}

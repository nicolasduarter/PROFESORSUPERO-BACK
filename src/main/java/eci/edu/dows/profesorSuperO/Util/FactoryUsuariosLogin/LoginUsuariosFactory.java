package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LoginUsuariosFactory {
    private final Map<String, LoginUsuario> estrategias;

    @Autowired
    public LoginUsuariosFactory(List<LoginUsuario> estrategiasList) {
        this.estrategias = estrategiasList.stream()
                .collect(Collectors.toMap(
                        e -> {
                            if (e instanceof LoginEstudiante) return Permisos.ESTUDIANTE.toString();
                            if (e instanceof LoginDecanatura) return Permisos.DECANATURA.toString();
                            if (e instanceof LoginAdministrador) return Permisos.ADMINNISTRADOR.toString();
                            throw new RuntimeException("Tipo no encontrado");
                        },
                        e -> e
                ));
    }

    public LoginUsuario obtenerEstrategia(String rol) {
        LoginUsuario estrategia = estrategias.get(rol.toUpperCase());
        if (estrategia == null) {
            throw new RuntimeException("Rol no encontrado: " + rol);
        }
        return estrategia;
    }



}

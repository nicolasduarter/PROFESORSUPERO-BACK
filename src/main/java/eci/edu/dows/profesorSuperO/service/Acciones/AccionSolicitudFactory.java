package eci.edu.dows.profesorSuperO.service.Acciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccionSolicitudFactory {

    private final Map<String, AccionSolicitudCommand> comandos = new HashMap<>();

    @Autowired
    public AccionSolicitudFactory(List<AccionSolicitudCommand> comandosList) {
        comandosList.forEach(comando -> {comandos.put(comando.getTipoSolicitud().toLowerCase(),comando);});
    }

    public AccionSolicitudCommand obtenerComando(String nombre) {
        AccionSolicitudCommand comando = comandos.get(nombre.toLowerCase());
        if (comando == null) {
            throw new IllegalArgumentException("Acción no no encontrada: " + nombre);
        }
        return comando;
    }
}

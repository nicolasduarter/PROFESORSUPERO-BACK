package eci.edu.dows.profesorSuperO.service.Acciones;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccionSolicitudFactory {

    private final Map<AccionesSolicitud, AccionSolicitudCommand> comandos = new HashMap<>();

    @Autowired
    public AccionSolicitudFactory(List<AccionSolicitudCommand> comandosList) {
        comandosList.forEach(comando -> comandos.put(comando.getTipoSolicitud(), comando));
    }

    public AccionSolicitudCommand obtenerComando(AccionesSolicitud accion) {
        AccionSolicitudCommand comando = comandos.get(accion);
        if (comando == null) {
            throw new IllegalArgumentException("Acción no encontrada: " + accion);
        }
        return comando;
    }
}

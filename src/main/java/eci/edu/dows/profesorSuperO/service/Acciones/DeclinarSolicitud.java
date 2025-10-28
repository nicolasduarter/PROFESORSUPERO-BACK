package eci.edu.dows.profesorSuperO.service.Acciones;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;
import org.springframework.stereotype.Component;

@Component
public class DeclinarSolicitud implements AccionSolicitudCommand {

    @Override
    public void accionSolicitud(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.RECHAZADA);
    }

    @Override
    public AccionesSolicitud getTipoSolicitud() {
        return AccionesSolicitud.DECLINAR;
    }
}

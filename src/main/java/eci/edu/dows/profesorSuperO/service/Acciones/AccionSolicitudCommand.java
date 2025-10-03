package eci.edu.dows.profesorSuperO.service.Acciones;

import eci.edu.dows.profesorSuperO.model.Solicitud;

public interface AccionSolicitudCommand {
    void accionSolicitud(Solicitud solicitud);
    String getTipoSolicitud();
}

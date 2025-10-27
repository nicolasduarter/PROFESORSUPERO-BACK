package eci.edu.dows.profesorSuperO.service.Acciones;

import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;

public interface AccionSolicitudCommand {
    void accionSolicitud(Solicitud solicitud);
    AccionesSolicitud getTipoSolicitud();
}

package eci.edu.dows.profesorSuperO.service;


import eci.edu.dows.profesorSuperO.model.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import org.springframework.stereotype.Component;

@Component
public class AceptarSolicitud implements AccionSolicitudCommand {
    @Override
    public void accionSolicitud(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.APROBADA);
    }


}

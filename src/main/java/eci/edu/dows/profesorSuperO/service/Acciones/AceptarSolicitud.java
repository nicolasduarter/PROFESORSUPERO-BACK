package eci.edu.dows.profesorSuperO.service.Acciones;


import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import org.springframework.stereotype.Component;

@Component
public class AceptarSolicitud implements AccionSolicitudCommand {
    @Override
    public void accionSolicitud(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.APROBADA);
    }

    @Override
    public String getTipoSolicitud() {
        return AccionesSolicitud.ACEPTAR.toString();
    }

}

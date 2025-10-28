package eci.edu.dows.profesorSuperO.service.Acciones;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;
import org.springframework.stereotype.Component;

@Component
public class SolicitarInfoSolicitud implements AccionSolicitudCommand {

    @Override
    public void accionSolicitud(Solicitud solicitud) {
        solicitud.setEstado(EstadoSolicitud.INFORMACION_ADICIONAL);
    }

    @Override
    public AccionesSolicitud getTipoSolicitud() {
        return AccionesSolicitud.SOLICITAR_INFO;
    }
}

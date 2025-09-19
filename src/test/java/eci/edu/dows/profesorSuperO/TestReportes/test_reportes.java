package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GestionReportesTest {

    private GestionSolicitudes gestion;

    @Before
    public void setUp() {
        gestion = new GestionSolicitudes();
    }

    @Test
    public void testHistorialCambiosPorEstudiante() {
        SolicitudCambio solicitud1 = new SolicitudCambio("1000087659", "G1", "G2", "Cambio...");
        SolicitudCambio solicitud2 = new SolicitudCambio("1000087659", "G2", "G3", "Cambio...");

        gestion.AgregarSolicitud(solicitud1);
        gestion.AgregarSolicitud(solicitud2);

        List<SolicitudCambio> historial = gestion.getHistorialPorEstudiante("1000087659");

        assertEquals(2, historial.size());
        assertTrue(historial.contains(solicitud1));
        assertTrue(historial.contains(solicitud2));
    }

    @Test
    public void testGruposMasSolicitados() {
        gestion.AgregarSolicitud(new SolicitudCambio("1000087659", "G1", "G2", "Cambio 1"));
        gestion.AgregarSolicitud(new SolicitudCambio("1000087658", "G1", "G2", "Cambio 2"));
        gestion.AgregarSolicitud(new SolicitudCambio("1000087657", "G3", "G2", "Cambio 3"));

        String grupoMasSolicitado = gestion.getGrupoMasSolicitado();

        assertEquals("G2", grupoMasSolicitado);
    }

    @Test
    public void testTasaAprobacionVsRechazo() {
        SolicitudCambio solicitud1 = new SolicitudCambio("1000087659", "G1", "G2", "Cambio 1");
        SolicitudCambio solicitud2 = new SolicitudCambio("1000087658", "G3", "G4", "Cambio 2");

        gestion.AgregarSolicitud(solicitud1);
        gestion.AgregarSolicitud(solicitud2);

        gestion.responderSolicitud(solicitud1, EstadoSolicitud.APROBADA);
        gestion.responderSolicitud(solicitud2, EstadoSolicitud.RECHAZADA);

        double tasa = gestion.getTasaAprobacion();

        assertEquals(0.5, tasa, 0.01);
    }
}


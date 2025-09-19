package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class test_solicitudes {

    private GestionSolicitudes gestion;

    @BeforeEach
    public void setUp() {
        gestion = new GestionSolicitudes();
    }

    @Test
    public void CreacionSolicitud() {
        Solicitud s = new Solicitud("1000096654", "Matemáticas");
        gestion.AgregarSolicitud(s);
        gestion.DocumentarSolicitud(s);

        assertEquals(1, gestion.getSolicitudes().size());

        assertEquals(1, gestion.getLogs().size());
        assertTrue(gestion.getLogs().get(0).contains("Solicitud creada"));
    }


    @Test
    public void testAsignacionPrioridadOrdenLlegada() {
        Solicitud s1 = new Solicitud("1000096699", "civil");
        Solicitud s2 = new Solicitud("1000096123", "mecanica");

        gestion.AgregarSolicitud(s1);
        gestion.AgregarSolicitud(s2);

        List<Solicitud> lista = gestion.getSolicitudes();
        assertEquals("1000096699", lista.get(0).getIdEstudiante());
        assertEquals("1000096123", lista.get(1).getIdEstudiante());
    }

    @Test
    public void testCambioEstado() {
        Solicitud s = new Solicitud("1000096699", "sistemas");
        gestion.AgregarSolicitud(s);

        s.setEstado(Estado.EN_PROCESO);
        assertEquals(Estado.EN_PROCESO, s.getEstado());

        s.setEstado(Estado.APROBADA);
        assertEquals(Estado.APROBADA, s.getEstado());
    }

    @Test
    public void testRestriccionSobrecupo() {
        Solicitud s = new Solicitud("Laura", "Matemáticas");

        String esperada = s.Sobrecupo(clase);

        assertEquals("La solicitud no puede aprobarse por sobrecupo", esperada);
    }

    @Test
    public void testRestriccionCruceHorario() {
        Solicitud s = new Solicitud("Carlos", "sistemas");
        String esperada = s.cruceHorario(horario);

        assertEquals("La solicitud no puede aprobarse por cruce horario", esperada);
    }

    @Test
    public void testGeneracionReportes() {
        Solicitud s1 = new Solicitud("Ana", "sistemas");


        gestion.AgregarSolicitud(s1);
        gestion.EnviarSolicitud(s1,decanatura);
        s1.setEstado(APROBADA);
        gestion.EnviarSolicitud(s1,estudiante);


        Reporte reporte = gestion.generarReporte();

        assertEquals("solicitud creada", reporte.getEntrada(0));
        assertEquals("solicitud enviada a decanatura", reporte.getEntrada(1));
        assertEquals("solicitud respondida", reporte.getentrada(2));
        assertEquals("solicitud enviada a estudiante", reporte.getentrada(3));
    }
}


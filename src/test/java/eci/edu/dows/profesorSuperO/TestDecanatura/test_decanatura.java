package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class test_decanatura {

    private GestionDecanatura gestion;
    private SolicitudCambio solicitud;

    @BeforeEach
    public void setUp() {
        gestion = new GestionDecanatura();
        solicitud = new SolicitudCambio("DOSW", "G1", "G2", "Conflicto de horario");
        gestion.setFacultad(facultad);
        gestion.recibirSolicitud(solicitud);
    }

    @Test
    public void testAccesoRestringidoFacultad() {
        List<SolicitudCambio> solicitudes = gestion.getSolicitudesPorFacultad("sistemas");
        assertEquals(1, solicitudes.size());


        List<SolicitudCambio> otras = gestion.getSolicitudesPorFacultad(facultad);
        assertTrue(otras.isEmpty());
    }

    @Test
    public void testConsultaHorarioEstudiante() {
        Horario horario = gestion.consultarHorarioEstudiante(solicitud.getEstudianteId());
        assertNotNull(horario);
    }

    @Test
    public void testResponderSolicitudAprobada() {
        solicitud.responderSolicitud(EstadoSolicitud.APROBADA);
        assertEquals(EstadoSolicitud.APROBADA, solicitud.getEstado());
    }

    @Test
    public void testRestriccionGrupoDestinoLleno() {
        Clase grupo = solicitud.getGrupoCambio;
        gestion.setGrupoCapacidad(grupo, 30, 30);

        solicitud.setEstado(solicitud, EstadoSolicitud.APROBADA);

        assertNotEquals(EstadoSolicitud.APROBADA, solicitud.getEstado());
    }

    @Test
    public void testMonitoreoCargaGrupos() {
        Clase grupo = solicitud.getGrupoCambio;
        gestion.setGrupoCapacidad(grupo, 30, 30);
        assertTrue(gestion.grupoEnAlerta("G2"));
    }

    @Test
    public void testPeriodoHabilitado() {
        gestion.setPeriodoHabilitado(false);
        gestion.responderSolicitud(solicitud, EstadoSolicitud.APROBADA);

        assertNotEquals(EstadoSolicitud.APROBADA, solicitud.getEstado());
    }

    @Test
    public void testSoloDecanoOModificarCupos() {
        Clase grupo = solicitud.getGrupoCambio;
        boolean resultado = gestion.modificarCupo(grupo, 60, "docente");
        assertFalse(resultado);

        resultado = gestion.modificarCupo(grupo, 60, "decano");
        assertTrue(resultado);
    }
}


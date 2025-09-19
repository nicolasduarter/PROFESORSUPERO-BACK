package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class test_Estudiantes {

    private GestionEstudiantes gestion;
    private Estudiante estudiante;

    @BeforeEach
    public void setUp() {
        gestion = new GestionEstudiantes();
        estudiante = new Estudiante("1000098888", "juan", "clave");
        gestion.registrarEstudiante(estudiante);
    }

    @Test
    public void testRegistroYAutenticacion() {
        assertTrue(gestion.autenticar("1000098888", "clave123"));
        assertFalse(gestion.autenticar("1000098888", "claveIncorrecta"));
    }

    @Test
    public void testConsultaHorarioActual() {
        Horario horario = gestion.consultarHorario(estudiante.getId());
        assertNotNull(horario);
    }

    @Test
    public void testCreacionSolicitudCambio() {
        SolicitudCambio solicitud = new SolicitudCambio("DOSW", "Grupo A", "Grupo B", "Cambio por...");
        gestion.agregarSolicitud(solicitud);

        assertEquals(1, gestion.getSolicitudesDe(estudiante.getId()).size());
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
    }

    @Test
    public void testRestriccionFueraDeFecha() {
        gestion.setFechaHabilitada(false);
        SolicitudCambio solicitud = new SolicitudCambio("DOSW", "G1", "G2", "cambio por...");

        gestion.crearSolicitud(estudiante.getId(), solicitud);

        assertTrue(gestion.getSolicitudesDe(estudiante.getId()).isEmpty());
    }


    @Test
    public void testRestriccionMateriaCancelada() {
        estudiante.cancelarMateria("Dosw");
        SolicitudCambio solicitud = new SolicitudCambio("Dosw", "G1", "G2", "");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            gestion.crearSolicitud(estudiante.getId(), solicitud);
        });

        assertEquals("No se puede solicitar cambio de una materia cancelada en el mismo semestre.", ex.getMessage());
    }

    @Test
    public void testNumeroRadicadoYPrioridad() {
        SolicitudCambio s1 = new SolicitudCambio("Matemáticas", "G1", "G2", "");
        SolicitudCambio s2 = new SolicitudCambio("Física", "G1", "G2", "");

        gestion.crearSolicitud(estudiante.getId(), s1);
        gestion.crearSolicitud(estudiante.getId(), s2);

        assertEquals(1, s1.getNumeroRadicado());
        assertEquals(2, s2.getNumeroRadicado());
        assertTrue(s1.getNumeroRadicado() < s2.getNumeroRadicado());
    }
}


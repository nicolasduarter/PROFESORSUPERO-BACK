package eci.edu.dows.profesorSuperO.SolicitudController;

import eci.edu.dows.profesorSuperO.controller.SolicitudController;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.HistorialDecisionDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.service.SolicitudService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias (sin capa web) para SolicitudController siguiendo AAA.
 */
@ExtendWith(MockitoExtension.class)
class SolicitudControllerUnitTest {

    @Mock
    private SolicitudService solicitudService;

    @InjectMocks
    private SolicitudController controller;

    @Test
    void crearSolicitudCambioGrupo_devuelveOkYBody() {
        SolicitudCambioGrupoDTO entrada = mock(SolicitudCambioGrupoDTO.class);
        SolicitudCambioGrupoDTO esperado = mock(SolicitudCambioGrupoDTO.class);
        when(solicitudService.crearSolicitudCambioGrupo(entrada)).thenReturn(esperado);

        ResponseEntity<SolicitudCambioGrupoDTO> resp = controller.crearSolicitudCambioGrupo(entrada);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(esperado, resp.getBody());
        verify(solicitudService).crearSolicitudCambioGrupo(entrada);
    }

    @Test
    void crearSolicitudCambioMateria_devuelveOkYBody() {

        SolicitudCambioMateriaDTO entrada = mock(SolicitudCambioMateriaDTO.class);
        SolicitudCambioMateriaDTO esperado = mock(SolicitudCambioMateriaDTO.class);
        when(solicitudService.crearSolicitudCambioMateria(entrada)).thenReturn(esperado);

        ResponseEntity<SolicitudCambioMateriaDTO> resp = controller.crearSolicitudCambioMateria(entrada);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(esperado, resp.getBody());
        verify(solicitudService).crearSolicitudCambioMateria(entrada);
    }

    @Test
    void consultarSolicitudes_devuelveOkYLista() {

        List<SolicitudDTO> esperado = Arrays.asList(mock(SolicitudDTO.class), mock(SolicitudDTO.class));
        when(solicitudService.consultarSolicitudes()).thenReturn(esperado);

        ResponseEntity<List<SolicitudDTO>> resp = controller.consultarSolicitudes();

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(esperado, resp.getBody());
        verify(solicitudService).consultarSolicitudes();
    }

    @Test
    void consultarSolicitudPorId_devuelveOkYBody() {

        String id = "s1";
        SolicitudDTO esperado = mock(SolicitudDTO.class);
        when(solicitudService.consultarSolicitudPorId(id)).thenReturn(esperado);

        ResponseEntity<SolicitudDTO> resp = controller.consultarSolicitudPorId(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(esperado, resp.getBody());
        verify(solicitudService).consultarSolicitudPorId(id);
    }

    @Test
    public void testConsultarHistorialDecisiones() {
        // Given
        String solicitudId = "SOL-123";
        List<HistorialDecisionDTO> historialMock = Arrays.asList(
                new HistorialDecisionDTO("HIST-1", EstadoSolicitud.PENDIENTE, EstadoSolicitud.APROBADA,
                        "Documentación completa", "profesor@eci.edu.co", LocalDateTime.now()),
                new HistorialDecisionDTO("HIST-2", EstadoSolicitud.APROBADA, EstadoSolicitud.RECHAZADA,
                        "Falta documentación", "decano@eci.edu.co", LocalDateTime.now())
        );

        when(solicitudService.consultarHistorialDecisiones(solicitudId)).thenReturn(historialMock);

        ResponseEntity<List<HistorialDecisionDTO>> response = controller.consultarHistorialDecisiones(solicitudId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(solicitudService, times(1)).consultarHistorialDecisiones(solicitudId);
    }

    @Test
    public void testActualizarEstadoSolicitudConHistorial() {
        String solicitudId = "SOL-123";
        EstadoSolicitud nuevoEstado = EstadoSolicitud.APROBADA;
        String comentario = "Test de comentario";
        String usuario = "test@eci.edu.co";

        SolicitudDTO solicitudMock = new SolicitudDTO();
        solicitudMock.setId(solicitudId);
        solicitudMock.setEstado("APROBADA");

        when(solicitudService.actualizarEstadoSolicitud(solicitudId, nuevoEstado, comentario, usuario))
                .thenReturn(solicitudMock);

        ResponseEntity<SolicitudDTO> response = controller.actualizarEstadoSolicitud(
                solicitudId, nuevoEstado, comentario, usuario);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("APROBADA", response.getBody().getEstado());
        verify(solicitudService, times(1)).actualizarEstadoSolicitud(solicitudId, nuevoEstado, comentario, usuario);
    }

    @Test
    void eliminarSolicitud_invocaServicioConId() {
        String id = "s1";

        controller.eliminarSolicitud(id);

        verify(solicitudService).eliminarSolicitud(id);
        verifyNoMoreInteractions(solicitudService);
    }
}
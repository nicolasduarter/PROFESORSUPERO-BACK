package eci.edu.dows.profesorSuperO.GrupoControllerTest;

import eci.edu.dows.profesorSuperO.controller.GrupoController;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.service.Implementaciones.GrupoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias directas sobre el controlador (sin capa web) usando Mockito.
 * Se sigue el patrón AAA en cada prueba.
 */
@ExtendWith(MockitoExtension.class)
class GrupoControllerUnitTest {

    @Mock
    private GrupoServiceImpl grupoServiceImpl;

    @InjectMocks
    private GrupoController controller;

    @Test
    void crearGrupo_devuelveOkYBody() {
        // Arrange
        GrupoDTO input = mock(GrupoDTO.class);
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.crearGrupo(input)).thenReturn(esperado);

        // Act
        ResponseEntity<GrupoDTO> respuesta = controller.crearGrupo(input);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).crearGrupo(input);
    }

    @Test
    void eliminarGrupo_invocaServicioConId() {
        String id = "grupo-123";

        controller.eliminarGrupo(id);

        verify(grupoServiceImpl).eliminarGrupo(id);
        verifyNoMoreInteractions(grupoServiceImpl);
    }

    @Test
    void modificarCuposGrupo_devuelveOkYBody() {
        String grupoId = "g1";
        int cupo = 35;
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.modificarCuposGrupo(grupoId, cupo)).thenReturn(esperado);

        ResponseEntity<GrupoDTO> respuesta = controller.modificarCuposMax(grupoId, cupo);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).modificarCuposGrupo(grupoId, cupo);
    }

    @Test
    void agregarEstudianteAGrupo_devuelveOkYBody() {
        String grupoId = "g1";
        String estudianteId = "e1";
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.agregarEstudianteAGrupo(grupoId, estudianteId)).thenReturn(esperado);

        ResponseEntity<GrupoDTO> respuesta = controller.agregarEstudianteAGrupo(grupoId, estudianteId);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).agregarEstudianteAGrupo(grupoId, estudianteId);
    }

//    @Test
//    void eliminarEstudianteAGrupo_devuelveOkYBody() {
//        String grupoId = "g1";
//        String estudianteId = "e2";
//        GrupoDTO esperado = mock(GrupoDTO.class);
//        when(grupoService.eliminarEstudianteAGrupo(grupoId, estudianteId)).thenReturn(esperado);
//
//        ResponseEntity<GrupoDTO> respuesta = controller.eliminarEstudianteAGrupo(estudianteId, grupoId);
//
//        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
//        assertSame(esperado, respuesta.getBody());
//        verify(grupoService).eliminarEstudianteAGrupo(grupoId, estudianteId);
//    }

    @Test
    void agregarProfesorAGrupo_devuelveOkYBody() {
        String grupoId = "g1";
        String profesorId = "p1";
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.agregarProfesorAGrupo(grupoId, profesorId)).thenReturn(esperado);

        ResponseEntity<GrupoDTO> respuesta = controller.agregarProfesorAGrupo(grupoId, profesorId);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).agregarProfesorAGrupo(grupoId, profesorId);
    }

    @Test
    void eliminarProfesorAGrupo_devuelveOkYBody() {
        String grupoId = "g1";
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.eliminarProfesorAGrupo(grupoId)).thenReturn(esperado);

        ResponseEntity<GrupoDTO> respuesta = controller.eliminarProfesorDeGrupo(grupoId);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).eliminarProfesorAGrupo(grupoId);
    }

    @Test
    void agregarClasesAGrupo_devuelveOkYBody() {
        String grupoId = "g1";
        List<ClaseDTO> clases = Arrays.asList(mock(ClaseDTO.class), mock(ClaseDTO.class));
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.agregarClasesAGrupo(grupoId, clases)).thenReturn(esperado);

        ResponseEntity<GrupoDTO> respuesta = controller.agregarClasesAGrupo(grupoId, clases);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).agregarClasesAGrupo(grupoId, clases);
    }

    @Test
    void eliminarClasesAGrupo_devuelveOkYBody() {
        String grupoId = "g1";
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.eliminarClasesAGrupo(grupoId)).thenReturn(esperado);

        ResponseEntity<GrupoDTO> respuesta = controller.eliminarClasesDeGrupo(grupoId);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).eliminarClasesAGrupo(grupoId);
    }

    // ✅ AGREGAR ESTOS TESTS NUEVOS a tu clase existente:

    @Test
    void consultarListaEspera_devuelveListaEstudiantes() {
        // Arrange
        String grupoId = "grupo-123";
        List<Estudiante> listaEsperaMock = Arrays.asList(
                mock(Estudiante.class),
                mock(Estudiante.class)
        );
        when(grupoServiceImpl.consultarListaEspera(grupoId)).thenReturn(listaEsperaMock);

        // Act
        ResponseEntity<List<Estudiante>> respuesta = controller.consultarListaEspera(grupoId);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(listaEsperaMock, respuesta.getBody());
        verify(grupoServiceImpl).consultarListaEspera(grupoId);
    }

    @Test
    void eliminarDeListaEspera_devuelveOkYBody() {
        // Arrange
        String grupoId = "grupo-123";
        String estudianteId = "estudiante-456";
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.eliminarDeListaEspera(grupoId, estudianteId)).thenReturn(esperado);

        // Act
        ResponseEntity<GrupoDTO> respuesta = controller.eliminarDeListaEspera(grupoId, estudianteId);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).eliminarDeListaEspera(grupoId, estudianteId);
    }

    @Test
    void agregarEstudianteAGrupo_conListaEspera_llamaServicioCorrectamente() {
        // Arrange
        String grupoId = "grupo-123";
        String estudianteId = "estudiante-456";
        GrupoDTO esperado = mock(GrupoDTO.class);
        when(grupoServiceImpl.agregarEstudianteAGrupo(grupoId, estudianteId)).thenReturn(esperado);

        // Act
        ResponseEntity<GrupoDTO> respuesta = controller.agregarEstudianteAGrupo(grupoId, estudianteId);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(esperado, respuesta.getBody());
        verify(grupoServiceImpl).agregarEstudianteAGrupo(grupoId, estudianteId);
    }

//    @Test
//    void eliminarEstudianteAGrupo_conPromocionAutomatica_llamaServicioCorrectamente() {
//        // Arrange
//        String grupoId = "grupo-123";
//        String estudianteId = "estudiante-456";
//        GrupoDTO esperado = mock(GrupoDTO.class);
//        when(grupoServiceImpl.eliminarEstudianteAGrupo(grupoId, estudianteId)).thenReturn(esperado);
//
//        // Act
//        ResponseEntity<GrupoDTO> respuesta = controller.eliminarEstudianteAGrupo(estudianteId, grupoId);
//
//        // Assert
//        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
//        assertSame(esperado, respuesta.getBody());
//        verify(grupoServiceImpl).eliminarEstudianteAGrupo(grupoId, estudianteId);
//    }
}
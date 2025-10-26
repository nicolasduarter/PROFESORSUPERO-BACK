//package eci.edu.dows.profesorSuperO.MateriaControllerTest;
//
//
//import eci.edu.dows.profesorSuperO.controller.MateriaController;
//import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
//import eci.edu.dows.profesorSuperO.service.Implementaciones.MateriaServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// * Pruebas unitarias (sin capa web) para MateriaController siguiendo AAA.
// */
//@ExtendWith(MockitoExtension.class)
//class MateriaControllerUnitTest {
//
//    @Mock
//    private MateriaServiceImpl materiaServiceImpl;
//
//    @InjectMocks
//    private MateriaController controller;
//
//    @Test
//    void crearMateria_devuelveOkYBody() {
//
//        MateriaDTO entrada = mock(MateriaDTO.class);
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.crearMateria(entrada)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.crearMateria(entrada);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).crearMateria(entrada);
//    }
//
//    @Test
//    void obtenerMateriaPorID_devuelveOkYBody() {
//
//        String id = "m1";
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.buscarPorId(id)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.obtenerMateriaPorID(id);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).buscarPorId(id);
//    }
//
//    @Test
//    void obtenerMateriasPorNombre_devuelveOkYLista() {
//
//        String nombre = "Calculo";
//        List<MateriaDTO> esperado = Arrays.asList(mock(MateriaDTO.class), mock(MateriaDTO.class));
//        when(materiaServiceImpl.findByNombre(nombre)).thenReturn(esperado);
//
//        ResponseEntity<List<MateriaDTO>> resp = controller.obtenerMateriasPorNombre(nombre);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).findByNombre(nombre);
//    }
//
//    @Test
//    void borrarMateria_devuelveNoContent() {
//
//        String id = "m1";
//
//        ResponseEntity<Void> resp = controller.borrarMateria(id);
//
//        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
//        assertNull(resp.getBody());
//        verify(materiaServiceImpl).eliminarMateriaPorId(id);
//    }
//
//    @Test
//    void actualizarCreditos_devuelveOkYBody() {
//
//        String id = "m1";
//        int creditos = 4;
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.actualizarCreditos(id, creditos)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.actualizarCreditos(id, creditos);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).actualizarCreditos(id, creditos);
//    }
//
//    @Test
//    void actualizarNombre_devuelveOkYBody() {
//
//        String id = "m1";
//        String nombre = "Programacion";
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.actualizarNombre(id, nombre)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.actualizarNombre(id, nombre);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).actualizarNombre(id, nombre);
//    }
//
//    @Test
//    void agregarPrerequisitos_devuelveOkYBody() {
//
//        String id = "m1";
//        List<MateriaDTO> prereqs = Arrays.asList(mock(MateriaDTO.class));
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.agregarPrerequisitos(id, prereqs)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.agregarPrerequisitos(id, prereqs);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).agregarPrerequisitos(id, prereqs);
//    }
//
//    @Test
//    void eliminarPrerequisitos_devuelveOkYBody() {
//
//        String id = "m1";
//        List<MateriaDTO> prereqs = Arrays.asList(mock(MateriaDTO.class));
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.eliminarPrerequisitos(id, prereqs)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.eliminarPrerequisitos(id, prereqs);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).eliminarPrerequisitos(id, prereqs);
//    }
//
//    @Test
//    void agregarPrerequisito_devuelveOkYBody() {
//        String id = "m1";
//        MateriaDTO prereq = mock(MateriaDTO.class);
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.agregarPrerequisito(id, prereq)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.agregarPrerequisito(id, prereq);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).agregarPrerequisito(id, prereq);
//    }
//
//    @Test
//    void eliminarPrerequisito_devuelveOkYBody() {
//        String id = "m1";
//        MateriaDTO prereq = mock(MateriaDTO.class);
//        MateriaDTO esperado = mock(MateriaDTO.class);
//        when(materiaServiceImpl.eliminarPrerequisito(id, prereq)).thenReturn(esperado);
//
//        ResponseEntity<MateriaDTO> resp = controller.eliminarPrerequisito(id, prereq);
//
//        assertEquals(HttpStatus.OK, resp.getStatusCode());
//        assertSame(esperado, resp.getBody());
//        verify(materiaServiceImpl).eliminarPrerequisito(id, prereq);
//    }
//}
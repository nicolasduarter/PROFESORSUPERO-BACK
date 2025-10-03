package eci.edu.dows.profesorSuperO.UsuarioControllerTest;

import eci.edu.dows.profesorSuperO.controller.UsuarioController;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias (sin capa web) para UsuarioController siguiendo AAA.
 */
@ExtendWith(MockitoExtension.class)
class UsuarioControllerUnitTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController controller;

    @Test
    void crearEstudiante_devuelveDTODelServicio() {

        EstudianteDTO entrada = mock(EstudianteDTO.class);
        EstudianteDTO esperado = mock(EstudianteDTO.class);
        when(usuarioService.crearEstudiante(entrada)).thenReturn(esperado);

        EstudianteDTO resp = controller.crearEstudiante(entrada);

        assertSame(esperado, resp);
        verify(usuarioService).crearEstudiante(entrada);
    }

    @Test
    void crearProfesor_devuelveDTODelServicio() {

        ProfesorDTO entrada = mock(ProfesorDTO.class);
        ProfesorDTO esperado = mock(ProfesorDTO.class);
        when(usuarioService.crearProfesor(entrada)).thenReturn(esperado);

        ProfesorDTO resp = controller.crearProfesor(entrada);

        assertSame(esperado, resp);
        verify(usuarioService).crearProfesor(entrada);
    }

    @Test
    void eliminarUsuario_invocaServicioConId() {
        String id = "user-123";

        controller.eliminarUsuario(id);

        verify(usuarioService).eliminarUsuario(id);
        verifyNoMoreInteractions(usuarioService);
    }
}
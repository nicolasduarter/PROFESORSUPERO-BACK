package eci.edu.dows.profesorSuperO.ClaseServiceTest;

import eci.edu.dows.profesorSuperO.service.Implementaciones.ClaseServiceImpl;
import eci.edu.dows.profesorSuperO.Util.Mappers.ClaseMapper;
import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.ClaseDTO;
import eci.edu.dows.profesorSuperO.repository.ClaseRepository;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClaseServiceImplTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private ClaseRepository claseRepository;

    @Mock
    private ClaseMapper claseMapper;

    @InjectMocks
    private ClaseServiceImpl claseServiceImpl;

    private Clase clase;
    private ClaseDTO claseDTO;

    @BeforeEach
    void setUp() {
        clase = new Clase("1", "101", DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0));
        claseDTO = new ClaseDTO("1", LocalTime.of(8, 0), LocalTime.of(10, 0), "101", DayOfWeek.MONDAY);
    }

    @Test
    void crearClase_deberiaGuardarYRetornarDTO() {
        when(claseMapper.toClass(claseDTO)).thenReturn(clase);
        when(claseRepository.save(clase)).thenReturn(clase);
        when(claseMapper.toDTO(clase)).thenReturn(claseDTO);

        ClaseDTO resultado = claseServiceImpl.crearClase(claseDTO);

        assertNotNull(resultado);
        assertEquals("1", resultado.getIdClase());
        verify(claseRepository).save(clase);
    }

    @Test
    void modificarHoraInicio_deberiaActualizarHoraInicio() {
        LocalTime nuevoInicio = LocalTime.of(9, 0);
        when(claseRepository.findById("1")).thenReturn(Optional.of(clase));
        when(claseRepository.save(any(Clase.class))).thenReturn(clase);
        when(claseMapper.toDTO(clase)).thenReturn(claseDTO);

        ClaseDTO resultado = claseServiceImpl.modificarHoraInicio("1", nuevoInicio);

        assertEquals(nuevoInicio, clase.getHoraInicio());
        verify(claseRepository).save(clase);
    }

    @Test
    void modificarHoraInicio_deberiaLanzarExcepcionSiNoExiste() {
        when(claseRepository.findById("999")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> claseServiceImpl.modificarHoraInicio("999", LocalTime.of(9, 0)));

        assertEquals("Clase no encontrada", ex.getMessage());
    }

    @Test
    void modificarHoraFin_deberiaActualizarHoraFin() {
        LocalTime nuevoFin = LocalTime.of(11, 0);
        when(claseRepository.findById("1")).thenReturn(Optional.of(clase));
        when(claseRepository.save(any(Clase.class))).thenReturn(clase);
        when(claseMapper.toDTO(clase)).thenReturn(claseDTO);

        ClaseDTO resultado = claseServiceImpl.modificarHoraFin("1", nuevoFin);

        assertEquals(nuevoFin, clase.getHoraFin());
        verify(claseRepository).save(clase);
    }

    @Test
    void modificarSalonClase_deberiaActualizarSalon() {
        String nuevoSalon = "202";
        when(claseRepository.findById("1")).thenReturn(Optional.of(clase));
        when(claseRepository.save(any(Clase.class))).thenReturn(clase);
        when(claseMapper.toDTO(clase)).thenReturn(claseDTO);

        ClaseDTO resultado = claseServiceImpl.modificarSalonClase("1", nuevoSalon);

        assertEquals(nuevoSalon, clase.getSalon());
        verify(claseRepository).save(clase);
    }
}

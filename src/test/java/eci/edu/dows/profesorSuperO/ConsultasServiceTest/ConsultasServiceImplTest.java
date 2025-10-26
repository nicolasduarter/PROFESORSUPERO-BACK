//package eci.edu.dows.profesorSuperO.ConsultasServiceTest;
//
//import eci.edu.dows.profesorSuperO.service.Implementaciones.ConsultasServiceImpl;
//import eci.edu.dows.profesorSuperO.service.Implementaciones.SemaforoServiceImpl;
//import eci.edu.dows.profesorSuperO.Util.Mappers.GrupoMapper;
//import eci.edu.dows.profesorSuperO.Util.Mappers.HorarioMapper;
//import eci.edu.dows.profesorSuperO.Util.Mappers.ProfesorMapper;
//import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudMapper;
//import eci.edu.dows.profesorSuperO.model.Estudiante;
//import eci.edu.dows.profesorSuperO.model.Grupo;
//import eci.edu.dows.profesorSuperO.model.Horario;
//import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
//import eci.edu.dows.profesorSuperO.repository.GrupoRepository;
//import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ConsultasServiceImplTest {
//
//    @Mock
//    private EstudianteRepository estudianteRepository;
//
//    @Mock
//    private GrupoRepository grupoRepository;
//
//    @Mock
//    private SolicitudRepository solicitudRepository;
//
//    @Mock
//    private SemaforoServiceImpl semaforoServiceImpl;
//
//    @Mock
//    private HorarioMapper horarioMapper;
//
//    @Mock
//    private SolicitudMapper solicitudMapper;
//
//    @Mock
//    private ProfesorMapper profesorMapper;
//
//    @Mock
//    private GrupoMapper grupoMapper;
//
//    @InjectMocks
//    private ConsultasServiceImpl consultasServiceImpl;
//
//    @Test
//    void consultarCuposGrupo_retornaCuposCuandoGrupoExiste() {
//        Grupo grupo = new Grupo("g1", "nombre", null, 25, null, 30); // constructor existente
//        when(grupoRepository.findById("grupo1")).thenReturn(Optional.of(grupo));
//
//        int cupos = consultasServiceImpl.consultarCuposGrupo("grupo1");
//
//        assertEquals(25, cupos);
//        verify(grupoRepository).findById("grupo1");
//    }
//
//    @Test
//    void consultarCuposGrupo_retornaCeroCuandoGrupoNoExiste() {
//        when(grupoRepository.findById("grupoInexistente")).thenReturn(Optional.empty());
//
//        int cupos = consultasServiceImpl.consultarCuposGrupo("grupoInexistente");
//
//        assertEquals(0, cupos);
//        verify(grupoRepository).findById("grupoInexistente");
//    }
//
//    @Test
//    void consultarUltimoHorarioEstudiante_devuelveUltimoHorarioCuandoHayHorarios() {
//        Horario h1 = new Horario(1, Collections.emptyList());
//        Horario h2 = new Horario(2, Collections.emptyList());
//
//        Estudiante estudiante = new Estudiante();
//        ArrayList<Horario> horarios = new ArrayList<>();
//        horarios.add(h1);
//        horarios.add(h2);
//        estudiante.setHorarios(horarios);
//
//        when(estudianteRepository.findById("est1")).thenReturn(Optional.of(estudiante));
//
//        Horario resultado = consultasServiceImpl.consultarUltimoHorarioEstudiante("est1");
//
//        assertNotNull(resultado);
//        assertSame(h2, resultado, "Debe retornar la referencia al último Horario");
//        verify(estudianteRepository).findById("est1");
//    }
//
//    @Test
//    void consultarUltimoHorarioEstudiante_retornaNullSiNoHayHorarios() {
//        Estudiante estudiante = new Estudiante();
//        estudiante.setHorarios(new ArrayList<>()); // lista vacía
//
//        when(estudianteRepository.findById("est2")).thenReturn(Optional.of(estudiante));
//
//        Horario resultado = consultasServiceImpl.consultarUltimoHorarioEstudiante("est2");
//
//        assertNull(resultado);
//        verify(estudianteRepository).findById("est2");
//    }
//
//    @Test
//    void consultarUltimoHorarioEstudiante_retornaNullSiEstudianteNoExiste() {
//        when(estudianteRepository.findById("noExiste")).thenReturn(Optional.empty());
//
//        Horario resultado = consultasServiceImpl.consultarUltimoHorarioEstudiante("noExiste");
//
//        assertNull(resultado);
//        verify(estudianteRepository).findById("noExiste");
//    }
//}

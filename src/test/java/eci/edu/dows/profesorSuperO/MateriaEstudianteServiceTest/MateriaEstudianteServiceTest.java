package eci.edu.dows.profesorSuperO.MateriaEstudianteServiceTest;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import eci.edu.dows.profesorSuperO.repository.*;
import eci.edu.dows.profesorSuperO.service.GrupoService;
import eci.edu.dows.profesorSuperO.service.MateriaEstudianteService;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MateriaEstudianteServiceTest {

    @Mock
    private MateriaEstudianteRepository materiaEstudianteRepository;
    @Mock
    private EstudianteRepository estudianteRepository;
    @Mock
    private MateriaRepository materiaRepository;
    @Mock
    private GrupoRepository grupoRepository;
    @Mock
    private GrupoService grupoService;

    @InjectMocks
    private MateriaEstudianteService materiaEstudianteService;

    private Estudiante estudiante;
    private Materia materia;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();
        estudiante.setId("E001");
        estudiante.setUsuario("juan");

        materia = new Materia();
        materia.setId("M001");
        materia.setNombre("Programación 1");

        ReflectionTestUtils.setField(materiaEstudianteService, "estudianteRepository", estudianteRepository);
        ReflectionTestUtils.setField(materiaEstudianteService, "materiaRepository", materiaRepository);
        ReflectionTestUtils.setField(materiaEstudianteService, "grupoRepository", grupoRepository);
        ReflectionTestUtils.setField(materiaEstudianteService, "grupoService", grupoService);
    }

    @Test
    void crearInscripcionMateria_deberiaCrearNuevaInscripcionCuandoNoExiste() {
        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E001", "M001"))
                .thenReturn(Optional.empty());

        MateriaEstudiante nueva = new MateriaEstudiante();
        when(materiaEstudianteRepository.save(any(MateriaEstudiante.class))).thenReturn(nueva);

        MateriaEstudiante resultado = materiaEstudianteService.crearInscripcionMateria(estudiante, materia);

        assertThat(resultado).isNotNull();
        verify(materiaEstudianteRepository).save(any(MateriaEstudiante.class));
    }

    @Test
    void crearInscripcionMateria_deberiaLanzarExcepcionSiYaExiste() {
        MateriaEstudiante existente = new MateriaEstudiante();
        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E001", "M001"))
                .thenReturn(Optional.of(existente));

        assertThatThrownBy(() -> materiaEstudianteService.crearInscripcionMateria(estudiante, materia))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("el estudiante ya esta inscrito");

        verify(materiaEstudianteRepository, never()).save(any());
    }

    @Test
    void aprobarMateria_deberiaActualizarEstadoNotaYFechaCuandoExiste() {
        MateriaEstudiante existente = new MateriaEstudiante();
        existente.setEstado(EstadoMateria.EN_CURSO);
        existente.setNota(0.0);

        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E001", "M001"))
                .thenReturn(Optional.of(existente));
        when(materiaEstudianteRepository.save(any(MateriaEstudiante.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        double notaEsperada = 4.5;

        MateriaEstudiante resultado = materiaEstudianteService.aprobarMateria("E001", "M001", notaEsperada);

        assertThat(resultado.getEstado()).isEqualTo(EstadoMateria.APROBADA);
        assertThat(resultado.getNota()).isEqualTo(notaEsperada);
        assertThat(resultado.getFechaAprobada()).isNotNull();

        verify(materiaEstudianteRepository).save(any(MateriaEstudiante.class));
    }

    @Test
    void aprobarMateria_deberiaLanzarExcepcionCuandoNoExiste() {
        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E001", "M001"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> materiaEstudianteService.aprobarMateria("E001", "M001", 4.0))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("no encontrado");

        verify(materiaEstudianteRepository, never()).save(any());
    }

    @Test
    void repobrarMateria_deberiaActualizarEstadoNotaEIncrementarIntentos() {
        MateriaEstudiante existente = new MateriaEstudiante();
        existente.setIntentos(1);
        existente.setEstado(EstadoMateria.EN_CURSO);
        existente.setNota(3.0);

        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E001", "M001"))
                .thenReturn(Optional.of(existente));
        when(materiaEstudianteRepository.save(any(MateriaEstudiante.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        double nuevaNota = 2.0;

        MateriaEstudiante resultado = materiaEstudianteService.repobrarMateria("E001", "M001", nuevaNota);

        assertThat(resultado.getEstado()).isEqualTo(EstadoMateria.REPROBADA);
        assertThat(resultado.getNota()).isEqualTo(nuevaNota);
        assertThat(resultado.getIntentos()).isEqualTo(2);

        verify(materiaEstudianteRepository).save(any(MateriaEstudiante.class));
    }

    @Test
    void repobrarMateria_deberiaLanzarExcepcionCuandoNoExiste() {
        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E001", "M001"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> materiaEstudianteService.repobrarMateria("E001", "M001", 2.5))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("no encontrado");

        verify(materiaEstudianteRepository, never()).save(any());
    }

    @Test
    void aprobarMateria_deberiaActualizarEstadoNotaYFecha() {
        String idEstudiante = "E001";
        String idMateria = "M001";
        double nota = 4.5;

        MateriaEstudiante existente = new MateriaEstudiante();
        existente.setEstado(EstadoMateria.EN_CURSO);
        existente.setNota(0.0);
        existente.setFechaAprobada(null);

        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria))
                .thenReturn(Optional.of(existente));
        when(materiaEstudianteRepository.save(any(MateriaEstudiante.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MateriaEstudiante resultado = materiaEstudianteService.aprobarMateria(idEstudiante, idMateria, nota);

        assertEquals(EstadoMateria.APROBADA, resultado.getEstado());
        assertEquals(nota, resultado.getNota(), 0.0001);
        assertNotNull(resultado.getFechaAprobada());
        assertTrue(resultado.getFechaAprobada().isEqual(LocalDate.now()));

        verify(materiaEstudianteRepository).findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria);
        verify(materiaEstudianteRepository).save(existente);

    }


    @Test
    void aprobarMateria_deberiaLanzarExcepcionSiNoExiste() {
        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id("E002", "M002"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                materiaEstudianteService.aprobarMateria("E002", "M002", 3.0));

        assertEquals("no encontrado", exception.getMessage());
        verify(materiaEstudianteRepository, never()).save(any());
    }

    @Test
    void reprobarMateria_deberiaActualizarEstadoNotaEIncrementarIntentos() {
        String idEstudiante = "E001";
        String idMateria = "M001";
        double nota = 2.0;

        MateriaEstudiante existente = new MateriaEstudiante();
        existente.setEstado(EstadoMateria.EN_CURSO);
        existente.setNota(0.0);
        existente.setIntentos(1);

        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria))
                .thenReturn(Optional.of(existente));
        when(materiaEstudianteRepository.save(any(MateriaEstudiante.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MateriaEstudiante resultado = materiaEstudianteService.repobrarMateria(idEstudiante, idMateria, nota);

        assertEquals(EstadoMateria.REPROBADA, resultado.getEstado());
        assertEquals(nota, resultado.getNota(), 0.0001);
        assertEquals(2, resultado.getIntentos());

        verify(materiaEstudianteRepository).findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria);
        verify(materiaEstudianteRepository).save(existente);
    }

    @Test
    void reprobarMateria_deberiaLanzarExcepcionSiNoExiste() {
        String idEstudiante = "E002";
        String idMateria = "M002";
        double nota = 1.5;

        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                materiaEstudianteService.repobrarMateria(idEstudiante, idMateria, nota));

        verify(materiaEstudianteRepository).findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria);
        verify(materiaEstudianteRepository, never()).save(any());
    }

    @Test
    void asignarMateriaYGrupo_deberiaAsignarCorrectamenteMateriaYGrupo() {
        String idEstudiante = "est1";
        String idMateria = "mat1";

        Estudiante estudiante = new Estudiante();
        estudiante.setId(idEstudiante);

        Materia materia = new Materia();
        materia.setId(idMateria);

        Profesor profesor = null;
        Grupo grupo = new Grupo("g1", "Grupo 1", profesor, 10, materia, 20);

        MateriaEstudiante materiaEstudiante = new MateriaEstudiante();
        materiaEstudiante.setMateria(materia);
        materiaEstudiante.setEstudiante(estudiante);
        materiaEstudiante.setEstado(EstadoMateria.EN_CURSO);

        when(estudianteRepository.findById(idEstudiante)).thenReturn(Optional.of(estudiante));
        when(materiaRepository.findById(idMateria)).thenReturn(Optional.of(materia));
        when(grupoRepository.findAll()).thenReturn(List.of(grupo));
        when(materiaEstudianteRepository.findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria))
                .thenReturn(Optional.empty());
        when(materiaEstudianteRepository.save(any(MateriaEstudiante.class)))
                .thenReturn(materiaEstudiante);

        MateriaEstudiante resultado = materiaEstudianteService.asignarMateriaYGrupo(idEstudiante, idMateria);

        assertNotNull(resultado);
        assertEquals(EstadoMateria.EN_CURSO, resultado.getEstado());
        assertEquals(estudiante, resultado.getEstudiante());
        assertEquals(materia, resultado.getMateria());

        verify(grupoService).agregarEstudianteAGrupo(grupo.getIdGrupo(), idEstudiante);
        verify(materiaEstudianteRepository).save(any(MateriaEstudiante.class));
    }

}
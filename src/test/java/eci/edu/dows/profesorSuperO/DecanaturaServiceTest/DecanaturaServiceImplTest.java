//package eci.edu.dows.profesorSuperO.DecanaturaServiceTest;
//
//import eci.edu.dows.profesorSuperO.Util.Mappers.FacultadMapper;
//import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudMapper;
//import eci.edu.dows.profesorSuperO.model.*;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.FacultadDTO;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudDTO;
//import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
//import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;
//import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
//import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudCommand;
//import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudFactory;
//import eci.edu.dows.profesorSuperO.service.Implementaciones.DecanaturaServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import java.util.*;
//import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.EstudianteDTO;
//import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
//import eci.edu.dows.profesorSuperO.Util.Mappers.EstudianteMapper;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class DecanaturaServiceImplTest {
//
//    @Mock
//    private SolicitudRepository solicitudRepository;
//
//    @Mock
//    private AccionSolicitudFactory accionSolicitudFactory;
//
//    @Mock
//    private SolicitudMapper solicitudMapper;
//
//    @Mock
//    private FacultadMapper facultadMapper;
//
//    @Mock
//    private EstudianteRepository estudianteRepository;
//
//    @Mock
//    private EstudianteMapper estudianteMapper;
//
//    @InjectMocks
//    private DecanaturaServiceImpl decanaturaServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCambiarEstado_success() {
//        Solicitud solicitud = mock(Solicitud.class);
//        SolicitudDTO dto = mock(SolicitudDTO.class);
//        AccionSolicitudCommand comando = mock(AccionSolicitudCommand.class);
//
//        when(solicitudRepository.findById("1")).thenReturn(Optional.of(solicitud));
//        when(accionSolicitudFactory.obtenerComando("aprobar")).thenReturn(comando);
//        when(solicitudRepository.save(solicitud)).thenReturn(solicitud);
//        when(solicitudMapper.toDTO(solicitud)).thenReturn(dto);
//
//        SolicitudDTO result = decanaturaServiceImpl.cambiarEstado("1", "aprobar");
//
//        assertNotNull(result);
//        verify(comando).accionSolicitud(solicitud);
//        verify(solicitudRepository).save(solicitud);
//    }
//
//    @Test
//    void testCambiarEstado_notFound() {
//        when(solicitudRepository.findById("99")).thenReturn(Optional.empty());
//        assertThrows(RuntimeException.class,
//                () -> decanaturaServiceImpl.cambiarEstado("99", "aprobar"));
//    }
//
//    @Test
//    void testObtenerSolicitudesPorFacultad() {
//        FacultadDTO facultadDTO = mock(FacultadDTO.class);
//        Facultad facultad = mock(Facultad.class);
//        Solicitud solicitud = mock(Solicitud.class);
//        SolicitudDTO dto = mock(SolicitudDTO.class);
//
//        when(facultadMapper.toFacultad(facultadDTO)).thenReturn(facultad);
//        when(solicitudRepository.findByFacultad(facultad)).thenReturn(List.of(solicitud));
//        when(solicitudMapper.toDTO(solicitud)).thenReturn(dto);
//
//        List<SolicitudDTO> result = decanaturaServiceImpl.obtenerSolicitudesPorFacultad(facultadDTO);
//
//        assertEquals(1, result.size());
//        assertEquals(dto, result.get(0));
//    }
//
//    @Test
//    void testObtenerSolicitudesPorPrioridad() {
//        Solicitud solicitud = mock(Solicitud.class);
//        SolicitudDTO dto = mock(SolicitudDTO.class);
//
//        when(solicitudRepository.findByPrioridad(2)).thenReturn(List.of(solicitud));
//        when(solicitudMapper.toDTO(solicitud)).thenReturn(dto);
//
//        List<SolicitudDTO> result = decanaturaServiceImpl.obtenerSolicitudesPorPrioridad(2);
//
//        assertEquals(1, result.size());
//        assertEquals(dto, result.get(0));
//    }
//
//    @Test
//    void testObtenerSolicitudesPendientes() {
//        FacultadDTO facultadDTO = mock(FacultadDTO.class);
//        Facultad facultad = mock(Facultad.class);
//        Solicitud pendiente = mock(Solicitud.class);
//        Solicitud noPendiente = mock(Solicitud.class);
//        SolicitudDTO dto = mock(SolicitudDTO.class);
//
//        when(facultadMapper.toFacultad(facultadDTO)).thenReturn(facultad);
//        when(solicitudRepository.findByFacultad(facultad)).thenReturn(List.of(pendiente, noPendiente));
//        when(pendiente.getEstado()).thenReturn(EstadoSolicitud.PENDIENTE);
//        when(noPendiente.getEstado()).thenReturn(EstadoSolicitud.APROBADA);
//        when(solicitudMapper.toDTO(pendiente)).thenReturn(dto);
//
//        List<SolicitudDTO> result = decanaturaServiceImpl.obtenerSolicitudesPendientes(facultadDTO);
//
//        assertEquals(1, result.size());
//        assertEquals(dto, result.get(0));
//    }
//
//    @Test
//    void testVerInformacionEstudiante_success() {
//        Estudiante estudiante = mock(Estudiante.class);
//        EstudianteDTO dto = mock(EstudianteDTO.class);
//
//        when(estudianteRepository.findById("e1")).thenReturn(Optional.of(estudiante));
//        when(estudianteMapper.toDTO(estudiante)).thenReturn(dto);
//
//        EstudianteDTO result = decanaturaServiceImpl.verInformacionEstudiante("e1");
//
//        assertNotNull(result);
//        assertEquals(dto, result);
//        verify(estudianteRepository).findById("e1");
//        verify(estudianteMapper).toDTO(estudiante);
//    }
//
//    @Test
//    void testVerInformacionEstudiante_notFound() {
//        when(estudianteRepository.findById("e2")).thenReturn(Optional.empty());
//        assertThrows(RuntimeException.class, () -> decanaturaServiceImpl.verInformacionEstudiante("e2"));
//        verify(estudianteRepository).findById("e2");
//    }
//}

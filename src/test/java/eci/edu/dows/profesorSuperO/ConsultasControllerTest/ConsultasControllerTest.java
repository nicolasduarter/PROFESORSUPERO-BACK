//package eci.edu.dows.profesorSuperO.ConsultasControllerTest;
//
//import eci.edu.dows.profesorSuperO.controller.ConsultasController;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.HorarioDTO;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.SemaforoDTO;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudDTO;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.ProfesorDTO;
//import eci.edu.dows.profesorSuperO.model.Horario;
//import eci.edu.dows.profesorSuperO.service.Implementaciones.ConsultasService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class ConsultasControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private ConsultasService consultasService;
//
//    @InjectMocks
//    private ConsultasController consultasController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(consultasController).build();
//    }
//
//    //@Test
//    //void consultarUltimoHorarioEstudiante_deberiaRetornarUltimoHorarioDTO() throws Exception {
//     //   HorarioDTO horario1 = new HorarioDTO(1, List.of());
//     //   HorarioDTO horario2 = new HorarioDTO(2, List.of());
//     //   HorarioDTO horario3 = new HorarioDTO(3, List.of());
//
//     //   when(consultasService.consultarUltimoHorarioEstudiante("est123"))
//     //           .thenReturn(new Horario(3, List.of()));
//
//      //  when(consultasService.consultarHorariosEstudiante("est123"))
//      //          .thenReturn(List.of(horario1, horario2, horario3));
//
//       // mockMvc.perform(get("/api/consultas/estudiante/{id}/ultimoHorario", "est123"))
//      //          .andExpect(status().isOk())
//      //          .andExpect(jsonPath("$.semestre").value(3));
//    //}
//
//
//    @Test
//    void consultarHorariosEstudiante_deberiaRetornarListaHorarios() throws Exception {
//        HorarioDTO horario = new HorarioDTO(1, List.of());
//        when(consultasService.consultarHorariosEstudiante("est123")).thenReturn(List.of(horario));
//
//        mockMvc.perform(get("/api/consultas/estudiante/{id}/horarios", "est123"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].semestre").value(1));
//    }
//
//    @Test
//    void consultarSolicitudesEstudiante_deberiaRetornarSolicitudes() throws Exception {
//        SolicitudDTO solicitud = new SolicitudDTO();
//        solicitud.setId("sol456");
//        solicitud.setEstado("PENDIENTE");
//
//        when(consultasService.consultarSolicitudesEstudiante("est123")).thenReturn(List.of(solicitud));
//
//        mockMvc.perform(get("/api/consultas/estudiante/{id}/solicitudes", "est123"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value("sol456"))
//                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
//    }
//
//    @Test
//    void consultarSemaforoEstudiante_deberiaRetornarSemaforo() throws Exception {
//        SemaforoDTO semaforo = new SemaforoDTO();
//        semaforo.setCreditosTotales(120);
//        semaforo.setCreditosActuales(90);
//
//        when(consultasService.consultarSemaforoEstudiante("est123")).thenReturn(semaforo);
//
//        mockMvc.perform(get("/api/consultas/estudiante/{id}/semaforo", "est123"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.creditosTotales").value(120))
//                .andExpect(jsonPath("$.creditosActuales").value(90));
//    }
//
//    @Test
//    void consultarProfesorGrupo_deberiaRetornarProfesor() throws Exception {
//        ProfesorDTO profesor = new ProfesorDTO();
//        profesor.setId("prof789");
//
//        when(consultasService.consultarProfesorGrupo("grupo1")).thenReturn(Optional.of(profesor));
//
//        mockMvc.perform(get("/api/consultas/grupo/{id}/profesor", "grupo1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("prof789"));
//    }
//
//    @Test
//    void consultarProfesorGrupo_deberiaRetornarNotFoundSiNoExiste() throws Exception {
//        when(consultasService.consultarProfesorGrupo("grupo1")).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/consultas/grupo/{id}/profesor", "grupo1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void consultarCuposGrupo_deberiaRetornarCupos() throws Exception {
//        when(consultasService.consultarCuposGrupo("grupo1")).thenReturn(5);
//
//        mockMvc.perform(get("/api/consultas/grupo/{id}/cupos", "grupo1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("5"));
//    }
//
//    @Test
//    void obtenerGrupo_deberiaRetornarGrupoDTO() throws Exception {
//        GrupoDTO grupo = new GrupoDTO();
//        grupo.setIdGrupo("grupo1");
//        grupo.setNombre("Grupo de Física");
//
//        when(consultasService.obtenerGrupo("grupo1")).thenReturn(grupo);
//
//        mockMvc.perform(get("/api/consultas/grupo/{id}", "grupo1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.idGrupo").value("grupo1"))
//                .andExpect(jsonPath("$.nombre").value("Grupo de Física"));
//    }
//}

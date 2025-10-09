package eci.edu.dows.profesorSuperO.DecanaturaControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import eci.edu.dows.profesorSuperO.controller.DecanaturaController;
import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.service.DecanaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DecanaturaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DecanaturaService decanaturaService;

    @InjectMocks
    private DecanaturaController decanaturaController;

    private ObjectMapper objectMapper;
    private SolicitudDTO solicitudDTO;
    private FacultadDTO facultadDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(decanaturaController).build();
        ObjectMapper objectMapper = new ObjectMapper();

        solicitudDTO = new SolicitudDTO();
        solicitudDTO.setId("sol123");
        solicitudDTO.setEstado("PENDIENTE");
        solicitudDTO.setMotivo("Cambio de grupo");
        solicitudDTO.setFecha(LocalDate.now());
        solicitudDTO.setPrioridad(2);
        solicitudDTO.setTipoSolicitud("Cambio");

        facultadDTO = new FacultadDTO();
        facultadDTO.setId("fac123");
        facultadDTO.setFacultad("Ingeniería");
        facultadDTO.setMaterias(new ArrayList<>());
    }

    @Test
    void obtenerSolicitudesPorFacultad_deberiaRetornarLista() throws Exception {
        when(decanaturaService.obtenerSolicitudesPorFacultad(any(FacultadDTO.class)))
                .thenReturn(List.of(solicitudDTO));

        mockMvc.perform(get("/decanatura/solicitudes/facultad/{facultad}", facultadDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("sol123"))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void obtenerSolicitudesPorPrioridad_deberiaRetornarLista() throws Exception {
        when(decanaturaService.obtenerSolicitudesPorPrioridad(2))
                .thenReturn(List.of(solicitudDTO));

        mockMvc.perform(get("/decanatura/solicitudes/prioridad/{prioridad}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].prioridad").value(2));
    }

    @Test
    void obtenerSolicitudesPendientes_deberiaRetornarLista() throws Exception {
        when(decanaturaService.obtenerSolicitudesPendientes(any(FacultadDTO.class)))
                .thenReturn(List.of(solicitudDTO));

        mockMvc.perform(get("/decanatura/solicitudes/pendientes/facultad/{facultad}", facultadDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void verInformacionEstudiante_deberiaRetornarEstudiante() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        estudianteDTO.setId("est1");
        estudianteDTO.setCorreo("correo1@uni.edu");
        estudianteDTO.setUsuario("ESTUDIANTE"); // tipo de usuario
        estudianteDTO.setPermiso("BÁSICO");
        estudianteDTO.setSemestre(5);

        FacultadDTO facultad = new FacultadDTO();
        facultad.setId("fac1");
        facultad.setFacultad("Ingeniería");
        estudianteDTO.setFacultad(facultad);

        when(decanaturaService.verInformacionEstudiante("est1")).thenReturn(estudianteDTO);

        mockMvc.perform(get("/decanatura/estudiantes/{estudianteId}", "est1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("est1"))
                .andExpect(jsonPath("$.correo").value("correo1@uni.edu"))
                .andExpect(jsonPath("$.usuario").value("ESTUDIANTE"))
                .andExpect(jsonPath("$.permiso").value("BÁSICO"))
                .andExpect(jsonPath("$.semestre").value(5))
                .andExpect(jsonPath("$.facultad.id").value("fac1"))
                .andExpect(jsonPath("$.facultad.facultad").value("Ingeniería"));

        verify(decanaturaService).verInformacionEstudiante("est1");
    }


    //@Test
    //void cambiarEstadoSolicitud_deberiaRetornarEstado() throws Exception {
     //   SolicitudDTO dto = new SolicitudDTO();
      //  dto.setId("sol123");
       // dto.setEstado("APROBADA");

       // when(decanaturaService.cambiarEstado(eq("sol123"), eq("APROBAR")))
      //          .thenReturn(dto);

      //  mockMvc.perform(patch("/solicitudes/{solicitudId}/accion", "sol123")
      //                  .contentType(MediaType.APPLICATION_JSON)
      //                  .content("\"APROBAR\""))
       //         .andExpect(status().isOk())
       //         .andExpect(jsonPath("$.estado").value("APROBADA"))
        //        .andExpect(jsonPath("$.id").value("sol123"));
    //}




}

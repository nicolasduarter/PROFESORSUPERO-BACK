package eci.edu.dows.profesorSuperO.ClaseControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import eci.edu.dows.profesorSuperO.controller.ClaseController;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.service.ClaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClaseService claseService;

    @InjectMocks
    private ClaseController claseController;

    private ObjectMapper objectMapper;
    private ClaseDTO claseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(claseController).build();
        objectMapper = new ObjectMapper();

        claseDTO = new ClaseDTO();
        claseDTO.setIdClase("1");
        claseDTO.setSalon("A101");
    }

    @Test
    void crearClase_deberiaRetornarClaseCreada() throws Exception {
        when(claseService.crearClase(any(ClaseDTO.class))).thenReturn(claseDTO);

        mockMvc.perform(post("/api/clases/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClase").value("1"))
                .andExpect(jsonPath("$.salon").value("A101"));
    }

    @Test
    void buscarClasePorId_deberiaRetornarClase() throws Exception {
        when(claseService.buscarClasePorId("1")).thenReturn(claseDTO);

        mockMvc.perform(get("/api/clases/clases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClase").value("1"))
                .andExpect(jsonPath("$.salon").value("A101"));
    }

    @Test
    void modificarHoraInicio_deberiaActualizarHora() throws Exception {
        ClaseDTO claseActualizada = new ClaseDTO();
        claseActualizada.setIdClase("1");
        when(claseService.modificarHoraInicio(eq("1"), any(LocalTime.class))).thenReturn(claseActualizada);

        mockMvc.perform(put("/api/clases/modificarHoraInicio")
                        .param("claseId", "1")
                        .param("inicio", "08:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClase").value("1"));
    }


    @Test
    void eliminarClase_deberiaRetornarStatusOk() throws Exception {
        doNothing().when(claseService).eliminarClase("1");

        mockMvc.perform(delete("/api/clases/1/clase"))
                .andExpect(status().isOk());
    }
}

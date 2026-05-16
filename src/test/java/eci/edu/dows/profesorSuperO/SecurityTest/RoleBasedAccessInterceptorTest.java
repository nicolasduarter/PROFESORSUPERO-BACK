package eci.edu.dows.profesorSuperO.SecurityTest;

import eci.edu.dows.profesorSuperO.config.RoleBasedAccessInterceptor;
import eci.edu.dows.profesorSuperO.controller.Usuarios.AdministradorController;
import eci.edu.dows.profesorSuperO.service.Implementaciones.AdministradorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoleBasedAccessInterceptorTest {

    private MockMvc mockMvc;

    @Mock
    private AdministradorServiceImpl administradorService;

    @InjectMocks
    private AdministradorController administradorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(administradorController)
                .addInterceptors(new RoleBasedAccessInterceptor())
                .build();
    }

    @Test
    void deleteAdmin_sinRolAutorizado_deberiaRetornar403() throws Exception {
        mockMvc.perform(delete("/administracion/{id}", "admin-1"))
                .andExpect(status().isForbidden());

        verifyNoInteractions(administradorService);
    }

    @Test
    void deleteAdmin_conRolAdministrador_deberiaPermitirAcceso() throws Exception {
        mockMvc.perform(delete("/administracion/{id}", "admin-1")
                        .header("X-USER-ROLE", "ADMINISTRATOR"))
                .andExpect(status().isNoContent());
    }
}

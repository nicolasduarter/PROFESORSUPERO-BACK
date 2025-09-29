package eci.edu.dows.profesorSuperO.ProfesorControllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import eci.edu.dows.profesorSuperO.controller.UsuarioController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfesorControllerTest {
    @Autowired
    private UsuarioController usuarioController;

    @Test
    void contextLoads() throws Exception {
        assertThat(usuarioController).isNotNull();
    }

}

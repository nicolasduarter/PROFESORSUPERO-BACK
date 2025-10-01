package eci.edu.dows.profesorSuperO.MappersTests;


import eci.edu.dows.profesorSuperO.Util.EstudianteMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Facultad;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static eci.edu.dows.profesorSuperO.model.Enums.Facultades.SISTEMAS;
import static eci.edu.dows.profesorSuperO.model.Enums.Permisos.ESTUDIANTE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MappersTest {

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Test
    public void testMapEstudianteDTO() {
        Estudiante e = new  Estudiante("Usuario",ESTUDIANTE,"correo","id",new Facultad(SISTEMAS),1);
        EstudianteDTO  eDto= estudianteMapper.toDTO(e);
        assertEquals(eDto.getId(),e.getId());
        assertEquals(eDto.getCorreo(),e.getCorreo());
        assertEquals(eDto.getSemestre(),e.getSemestre());
        assertEquals(eDto.getPermiso(),e.getPermiso().toString());
        assertEquals(eDto.getUsuario(),e.getUsuario());
        assertNotNull(eDto.getFacultad());
        assertEquals(eDto.getFacultad().getFacultad(),e.getFacultad().getFacultad().toString());

    }
}

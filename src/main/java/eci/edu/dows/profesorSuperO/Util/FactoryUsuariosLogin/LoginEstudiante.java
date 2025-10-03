package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;


import eci.edu.dows.profesorSuperO.Util.EstudianteMapper;
import eci.edu.dows.profesorSuperO.model.Credencial;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Usuario;
import eci.edu.dows.profesorSuperO.repository.CredencialRepository;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginEstudiante  implements LoginUsuario {

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Autowired
    private UsuarioRegistroMapper usuarioRegistroMapper;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    @Override
    public UsuarioLoginDTO loginUsuario(Usuario usuario) {
        Estudiante e = (Estudiante) usuario;
        UsuarioLoginDTO registroU = new UsuarioLoginDTO();
        registroU.setUsuario(e.getUsuario());
        registroU.setRol(e.getPermiso().toString());

        return registroU;
    }




    @Override
    public UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        EstudianteDTO estudianteDTO = usuarioRegistroMapper.usuarioRegistroDTOToEstudianteDTO(usuarioRegistroDTO);
        Estudiante estudiante = estudianteMapper.toEstudiante(estudianteDTO);

        estudiante = estudianteRepository.save(estudiante);

        Credencial cred = new Credencial();
        cred.setUsuario(usuarioRegistroDTO.getUsuario());
        cred.setConstra(usuarioRegistroDTO.getContra());
        cred.setUsuarioId(estudiante.getId());

        UsuarioRegistroSalidaDTO salida  = new UsuarioRegistroSalidaDTO();
        salida.setUsuario(usuarioRegistroDTO.getUsuario());
        salida.setId(estudiante.getId());
        salida.setRol(estudiante.getPermiso().toString());

        credencialRepository.save(cred);

        return salida;

    }
}

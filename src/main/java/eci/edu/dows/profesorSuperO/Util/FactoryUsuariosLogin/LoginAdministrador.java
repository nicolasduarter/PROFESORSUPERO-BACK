package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.Util.Mappers.AdministradorMapper;
import eci.edu.dows.profesorSuperO.model.Credencial;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.Usuarios.Administrador;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.repository.CredencialRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginAdministrador implements LoginUsuario {

    @Autowired
    private AdministradorMapper administradorMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    @Override
    public UsuarioLoginDTO loginUsuario(Usuario usuario) {
        Administrador admin = (Administrador) usuario;
        UsuarioLoginDTO registroU = new UsuarioLoginDTO();
        registroU.setUsuario(admin.getFullName());
        registroU.setRol(admin.getPermiso().toString());
        try {
            registroU.getClass().getMethod("setId", String.class).invoke(registroU, admin.getId());
        } catch (Exception ignored) {
        }
        return registroU;
    }

    @Override
    public UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        Administrador admin = new Administrador(
                usuarioRegistroDTO.getFullName(),           // fullName
                Permisos.ADMINISTRATOR,                     // permiso
                usuarioRegistroDTO.getCorreo(),             // correo
                null                                        // id -> Mongo genera
        );

        Administrador saved = usuarioRepository.save(admin);

        Credencial cred = new Credencial();

        cred.setUsuario(usuarioRegistroDTO.getFullName());
        cred.setConstra(usuarioRegistroDTO.getContra());
        cred.setUsuarioId(saved.getId());

        credencialRepository.save(cred);

        UsuarioRegistroSalidaDTO salida = new UsuarioRegistroSalidaDTO();
        salida.setId(saved.getId());
        salida.setUsuario(saved.getFullName());
        salida.setRol(saved.getPermiso().toString());

        return salida;
    }
}


package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.LoginUsuario;
import eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin.LoginUsuariosFactory;
import eci.edu.dows.profesorSuperO.Util.Security.PasswordSecurityUtil;
import eci.edu.dows.profesorSuperO.model.Credencial;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.LoginRequestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import eci.edu.dows.profesorSuperO.repository.CredencialRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    private final CredencialRepository credencialRepository;

    private final UsuarioRepository usuarioRepository;

    private final LoginUsuariosFactory loginUsuariosFactory;

    private final PasswordSecurityUtil passwordSecurityUtil;

    @Autowired
    private AutenticacionServiceImpl(CredencialRepository credencialRepository,
                                     UsuarioRepository usuarioRepository,
                                     LoginUsuariosFactory loginUsuariosFactory,
                                     PasswordSecurityUtil passwordSecurityUtil) {
        this.credencialRepository = credencialRepository;
        this.usuarioRepository = usuarioRepository;
        this.loginUsuariosFactory = loginUsuariosFactory;
        this.passwordSecurityUtil = passwordSecurityUtil;

    }


    public UsuarioLoginDTO autenticar(LoginRequestDTO loginDTO) {
        Credencial credencial = credencialRepository.findByUsuario(loginDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("Credenciales inválidas"));

        if (!passwordSecurityUtil.matches(loginDTO.getContra(), credencial.getConstra())) {
            throw new NotFoundException("Credenciales inválidas");
        }

        Usuario usuario = usuarioRepository.findById(credencial.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LoginUsuario estrategia = loginUsuariosFactory.obtenerEstrategia(usuario.getPermiso().toString());

        return estrategia.loginUsuario(usuario);
    }

    public UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        if (credencialRepository.findByUsuario(usuarioRegistroDTO.getFullName()).isPresent()) {
            throw new RuntimeException("Ya existe una cuenta con ese fullName");
        }

        LoginUsuario estrategia = loginUsuariosFactory.obtenerEstrategia(usuarioRegistroDTO.getPermiso());

        return estrategia.registrarUsuario(usuarioRegistroDTO);

    }

}

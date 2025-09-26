package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.EstudianteDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearEstudiante(EstudianteDTO dto) {
        Usuario nuevoUsuario = new Estudiante(dto.getUsuario(), dto.getClave(), dto.getPermiso(), dto.getCorreo(), dto.getId(), dto.getFacultadObjeto(), dto.getSemestre(), dto.getSemaforo()
        );

        return usuarioRepository.save(nuevoUsuario);
    }


    public Usuario crearProfesor(String usuario, String clave, String permiso, String correo, String id, List<Grupo> grupos) {

        Usuario nuevoUsuario = new Profesor(usuario, clave, permiso, correo, id, grupos);
        return usuarioRepository.save(nuevoUsuario);
    }

    public void eliminarUsuario(String usuario) {
        usuarioRepository.deleteById(usuario);
    }

    public void modificarClave(Usuario usuario,String clave) {
        usuario.setClave(clave);
        usuarioRepository.save(usuario);
    }

    public boolean verificarUsuario(String id, String correo, String clave) {
        return usuarioRepository.findById(id)
                .map(usuario -> usuario.getCorreo().equals(correo) && usuario.getClave().equals(clave))
                .orElse(false);
    }

}


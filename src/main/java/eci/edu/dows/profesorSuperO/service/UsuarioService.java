package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          EstudianteRepository estudianteRepository,
                          ProfesorRepository profesorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
    }

    public Estudiante crearEstudiante(EstudianteDTO dto) {
        Estudiante estudiante = new Estudiante(dto.getUsuario(), dto.getClave(), dto.getPermiso(), dto.getCorreo(), dto.getId(), dto.getFacultadObjeto(), dto.getSemestre(), dto.getSemaforo());
        return estudianteRepository.save(estudiante);
    }


    public Profesor crearProfesor(ProfesorDTO dto) {
        Profesor profesor = new Profesor(dto.getUsuario(), dto.getClave(), dto.getPermiso(), dto.getCorreo(), dto.getId(), dto.getGrupos());
        return profesorRepository.save(profesor);
    }

    public void eliminarUsuario(String usuario) {
        usuarioRepository.deleteById(usuario);
    }

    public void modificarClave(String id, String nuevaClave) {
        usuarioRepository.findById(id).ifPresent(usuario -> {
            usuario.setClave(nuevaClave);
            usuarioRepository.save(usuario);
        });
    }


    public boolean verificarUsuario(String id, String correo, String clave) {
        return usuarioRepository.findById(id)
                .map(usuario -> usuario.getCorreo().equals(correo) && usuario.getClave().equals(clave))
                .orElse(false);
    }

}


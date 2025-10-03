package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.EstudianteMapper;
import eci.edu.dows.profesorSuperO.Util.ProfesorMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;
    private final EstudianteMapper estudianteMapper;
    private final ProfesorMapper profesorMapper;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          EstudianteRepository estudianteRepository,
                          ProfesorRepository profesorRepository, EstudianteMapper estudianteMapper,
                          ProfesorMapper profesorMapper) {
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
        this.estudianteMapper = estudianteMapper;
        this.profesorMapper = profesorMapper;
    }

    public EstudianteDTO crearEstudiante(EstudianteDTO dto) {
        Estudiante estudiante = estudianteMapper.toEstudiante(dto);

        return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
    }


    public ProfesorDTO crearProfesor(ProfesorDTO dto) {
        Profesor p = profesorMapper.toProfesor(dto);

        return profesorMapper.toDTO(profesorRepository.save(p));
    }

    public void eliminarUsuario(String usuario) {
        usuarioRepository.deleteById(usuario);
    }


}


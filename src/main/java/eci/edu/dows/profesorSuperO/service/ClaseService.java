package eci.edu.dows.profesorSuperO.service;
import eci.edu.dows.profesorSuperO.Util.ClaseMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import io.micrometer.observation.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    private final EstudianteRepository estudianteRepository;
    private final ClaseRepository claseRepository;
    private final ClaseMapper claseMapper;

    @Autowired
    public ClaseService(EstudianteRepository estudianteRepository,
                        ClaseRepository claseRepository,ClaseMapper claseMapper) {
        this.estudianteRepository = estudianteRepository;
        this.claseRepository = claseRepository;
        this.claseMapper = claseMapper;
    }

    public Clase crearClase(ClaseDTO dto) {
        Clase clase = claseMapper.toClass(dto);
        return claseRepository.save(clase);
    }

    public Clase modificarHorarioClase(String claseId, String horario) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setFranjaHoraria(horario);
        return claseRepository.save(clase);
    }


    public Clase modificarSalonClase(String claseId, String salon) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setSalon(salon);
        return claseRepository.save(clase);
    }

    public Clase agregarEstudianteAClase(String claseId, String estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.agregarEstudiantes(estudiante);
        return claseRepository.save(clase);
    }

    public Clase eliminarEstudianteDeClase(String claseId, String estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.eliminarEstudiantes(estudiante);
        return claseRepository.save(clase);
    }
}
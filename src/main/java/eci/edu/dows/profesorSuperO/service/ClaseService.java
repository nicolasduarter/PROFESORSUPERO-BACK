package eci.edu.dows.profesorSuperO.service;
import eci.edu.dows.profesorSuperO.Util.ClaseMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import io.micrometer.observation.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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

    public Clase modificarHoraInicio(String claseId, LocalTime inicio) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setHoraInicio(inicio);
        return claseRepository.save(clase);
    }
    public Clase modificarHoraFin(String claseId, LocalTime fin) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setHoraFin(fin);
        return claseRepository.save(clase);
    }


    public Clase modificarSalonClase(String claseId, String salon) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setSalon(salon);
        return claseRepository.save(clase);
    }
}
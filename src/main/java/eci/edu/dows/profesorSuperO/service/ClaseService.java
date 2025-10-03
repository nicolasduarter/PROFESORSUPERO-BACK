package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.ClaseMapper;
import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.repository.ClaseRepository;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ClaseService {

    private final EstudianteRepository estudianteRepository;
    private final ClaseRepository claseRepository;
    private final ClaseMapper claseMapper;

    @Autowired
    public ClaseService(EstudianteRepository estudianteRepository,
                        ClaseRepository claseRepository,
                        ClaseMapper claseMapper) {
        this.estudianteRepository = estudianteRepository;
        this.claseRepository = claseRepository;
        this.claseMapper = claseMapper;
    }

    public ClaseDTO crearClase(ClaseDTO dto) {
        Clase clase = claseMapper.toClass(dto);
        Clase claseGuardada = claseRepository.save(clase);
        return claseMapper.toDTO(claseGuardada);
    }

    public ClaseDTO buscarClasePorId(String claseId) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        return claseMapper.toDTO(clase);
    }


    public ClaseDTO modificarHoraInicio(String claseId, LocalTime inicio) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setHoraInicio(inicio);
        Clase claseActualizada = claseRepository.save(clase);
        return claseMapper.toDTO(claseActualizada);
    }

    public ClaseDTO modificarHoraFin(String claseId, LocalTime fin) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setHoraFin(fin);
        Clase claseActualizada = claseRepository.save(clase);
        return claseMapper.toDTO(claseActualizada);
    }

    public ClaseDTO modificarSalonClase(String claseId, String salon) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setSalon(salon);
        Clase claseActualizada = claseRepository.save(clase);
        return claseMapper.toDTO(claseActualizada);
    }

    public void eliminarClase(String claseId) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        claseRepository.delete(clase);
    }

}

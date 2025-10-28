package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Mappers.ClaseMapper;
import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.ClaseDTO;
import eci.edu.dows.profesorSuperO.repository.ClaseRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
public class ClaseServiceImpl implements ClaseService {

    private final ClaseRepository claseRepository;
    private final ClaseMapper claseMapper;

    @Autowired
    public ClaseServiceImpl(ClaseRepository claseRepository,
                            ClaseMapper claseMapper) {
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

    public ClaseDTO updateDayOfWeekClase(String claseId, String dayOfWeek) {
        DayOfWeek d = DayOfWeek.valueOf(dayOfWeek);
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        clase.setDiaSemana(d);
        Clase claseActualizada = claseRepository.save(clase);
        return claseMapper.toDTO(claseActualizada);
    }



    public ClaseDTO updateClass(ClaseDTO dto) {
        Clase clase = claseRepository.findById(dto.getIdClase())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        clase.setSalon(dto.getSalon());
        clase.setHoraInicio(dto.getHoraInicio());
        clase.setHoraFin(dto.getHoraFin());
        clase.setDiaSemana(dto.getDiaSemana());
        Clase claseActualizada = claseRepository.save(clase);
        return claseMapper.toDTO(claseActualizada);
    }


    public void deleteClassByDay( String day) {
        DayOfWeek d = DayOfWeek.valueOf(day);
        List<Clase> clase = claseRepository.findByDiaSemana(d);

        claseRepository.deleteAll(clase);

    }



    public void eliminarClase(String claseId) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        claseRepository.delete(clase);
    }

}

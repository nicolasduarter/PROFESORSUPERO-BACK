package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;

import java.time.LocalTime;

public interface ClaseService {

    ClaseDTO crearClase(ClaseDTO dto);

    ClaseDTO buscarClasePorId(String claseId);

    ClaseDTO modificarHoraInicio(String claseId, LocalTime inicio);

    ClaseDTO modificarHoraFin(String claseId, LocalTime fin);

    ClaseDTO modificarSalonClase(String claseId, String salon);

    ClaseDTO updateDayOfWeekClase(String claseId, String dayOfWeek);

    ClaseDTO updateClass(ClaseDTO dto);

    void deleteClassByDay(String day);

    void eliminarClase(String claseId);
}

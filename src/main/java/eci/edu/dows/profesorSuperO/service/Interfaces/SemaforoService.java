package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;

public interface SemaforoService {
    SemaforoDTO crearSemaforo(SemaforoDTO dto, Estudiante estudiante);
    SemaforoDTO getSemaforoDTO(Estudiante estudiante);
}

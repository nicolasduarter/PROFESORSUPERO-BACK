package eci.edu.dows.profesorSuperO.service;


import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class DecanaturaService {
    private CalendarioAcademico calendarioAcademico;


    public void modificarPeriodohabilitado(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("La fecha de inicio es mayor a la fecha de final");
        }
        calendarioAcademico.setFechaInicio(start);
        calendarioAcademico.setFechaFinal(end);
    }
}

package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaseDTO {
    private String idClase;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String salon;
    private DayOfWeek diaSemana;
}

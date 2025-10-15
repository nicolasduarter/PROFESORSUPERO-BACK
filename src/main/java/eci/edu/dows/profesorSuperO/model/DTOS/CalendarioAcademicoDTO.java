package eci.edu.dows.profesorSuperO.model.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarioAcademicoDTO {
    private LocalDate start;
    private LocalDate end;
}

package eci.edu.dows.profesorSuperO.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.time.LocalTime;

@Document("Calendario")
public class CalendarioAcademico{
    private LocalDate start;
    private LocalDate end;



}

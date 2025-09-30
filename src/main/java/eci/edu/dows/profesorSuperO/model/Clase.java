package eci.edu.dows.profesorSuperO.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

@Getter
@Setter
@Document("Clase")
public class Clase {
    @Id
    private String idClase;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String salon;
    private DayOfWeek diaSemana;


    public Clase(String idClase, String salon,DayOfWeek diaSemana,LocalTime horaInicio,LocalTime horaFin ) {
        this.idClase = idClase;
        this.salon = salon;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }
}

package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("MateriasPorEstudiante")
public class MateriaEstudiante {
    @Id
    private String id;
    @NonNull
    @Indexed
    private Materia materia;
    @Indexed
    private Estudiante estudiante;
    private EstadoMateria estado;
    private int intentos;
    private double nota;
    private LocalDate fechaAprobada;
}

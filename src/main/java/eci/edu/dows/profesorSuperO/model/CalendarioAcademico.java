package eci.edu.dows.profesorSuperO.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Calendario")
public class CalendarioAcademico{
    @Id
    private String id;
    private LocalDate start;
    private LocalDate end;

}

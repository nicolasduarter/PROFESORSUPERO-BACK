package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Getter
@Setter
@Document("Facultad")
public class Facultad {
    @Id
    private String id;
    private ArrayList<Materia> materias;
    private Facultades facultadName;

    public Facultad(Facultades facultadName) {
        this.facultadName = facultadName;
        this.materias = new ArrayList<>();
    }

    public Facultad(){}


}

package eci.edu.dows.profesorSuperO.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document("Materia")
public class Materia {

    @Id
    private String id;
    @Indexed
    private String nombre;
    @Indexed
    private Boolean estado;
    private int  creditos;
    private List<Materia> prerequisitos;


    public Materia() {}

    public Materia(String id, String nombre,  int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.estado = false;
        this.creditos = creditos;
        this.prerequisitos = new ArrayList<>();
    }


}

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

    private int  creditos;
    private List<Materia> prerequisitos;


    public Materia() {}

    public Materia(String id, String nombre,  int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.prerequisitos = new ArrayList<>();
    }

    public void agregarPrerequisito(Materia materia) {
        this.prerequisitos.add(materia);
    }

    public void eliminarPrerequisito(Materia materia) {
        this.prerequisitos.remove(materia);
    }

    public void agregarPrerequisitos(List<Materia> materias) {
        this.prerequisitos.addAll(materias);
    }

    public void eliminarPrerequisitos(List<Materia> materias) {
        this.prerequisitos.addAll(materias);
    }

}

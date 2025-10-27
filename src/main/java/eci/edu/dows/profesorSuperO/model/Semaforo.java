package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@Document("Semaforo")
public class Semaforo {

    @Id
    private String id;
    private ArrayList<MateriaEstudiante> materiaEstudiante;
    private int creditosTotales;
    private int creditosActuales;
    private int creditosFaltantes;
    private String facultadId;
    private int materiasVistas;
    private int promedio;

    public Semaforo() {
        this.materiaEstudiante = new ArrayList<>();
    }



}

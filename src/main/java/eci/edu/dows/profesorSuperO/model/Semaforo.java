package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@Document("Semaforo")
public class Semaforo {

    private ArrayList<MateriaEstudiante> materiaEstudiante;
    private int creditosTotales;
    private int creditosActuales;
    private int creditosFaltantes;
    private Facultad facultad;
    private int materiasVistas;
    private int promedio;

    public Semaforo() {
        this.materiaEstudiante = new ArrayList<>();

    }



    public ArrayList<MateriaEstudiante> getMateriasAprobadas() {
        ArrayList<MateriaEstudiante> aprobadas = new ArrayList<>();
        materiaEstudiante.stream().
                filter(m->m.getEstado() == EstadoMateria.APROBADA).
                forEach(aprobadas::add);
        return aprobadas;
    }



    public ArrayList<MateriaEstudiante> getMateriasNoCursadas() {
        ArrayList<MateriaEstudiante> noCursadas = new ArrayList<>();
        materiaEstudiante.stream().
                filter(m->m.getEstado() == EstadoMateria.PENDIENTE).
                forEach(noCursadas::add);
        return noCursadas;
    }

    public ArrayList<MateriaEstudiante> getMateriasCanceladas() {
        ArrayList<MateriaEstudiante> canceladas = new ArrayList<>();
        materiaEstudiante.stream().
                filter(m->m.getEstado() == EstadoMateria.CANCELADA).
                forEach(canceladas::add);
        return canceladas;
    }


    public ArrayList<MateriaEstudiante> getMateriasReprobadas() {
        ArrayList<MateriaEstudiante> reprobadas = new ArrayList<>();
        materiaEstudiante.stream().
                filter(m->m.getEstado() == EstadoMateria.REPROBADA).
                forEach(reprobadas::add);
        return reprobadas;
    }

    public ArrayList<MateriaEstudiante> getMateriasEnCurso() {
        ArrayList<MateriaEstudiante> reprobadas = new ArrayList<>();
        materiaEstudiante.stream().
                filter(m->m.getEstado() == EstadoMateria.EN_CURSO).
                forEach(reprobadas::add);
        return reprobadas;
    }



    public MateriaEstudiante getMateriaById(String id) {
        Optional<MateriaEstudiante> materia =  materiaEstudiante.stream().filter(m->m.getId().equals(id)).findFirst();
        return   materia.orElse(null);

    }




}

package eci.edu.dows.profesorSuperO.model.DTOS;

import lombok.NoArgsConstructor;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GrupoDTO2 {
    private String id;
    private String nombre;
    private int cupo;
    private int cuposMax;

    private String profesorId;
    private String materiaId;
    private List<String> estudianteIds;
}
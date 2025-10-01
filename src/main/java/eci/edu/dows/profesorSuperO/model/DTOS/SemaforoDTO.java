package eci.edu.dows.profesorSuperO.model.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemaforoDTO {
    private List<MateriaEstudianteDTO> materias;
    private int creditosTotales;
    private int creditosActuales;
    private int creditosFaltantes;
    private int materiasVistas;
    private int promedio;
    private String facultad;
}

package eci.edu.dows.profesorSuperO.model.DTOS.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {
    private String id;
    private String nombre;
    private int  creditos;
}

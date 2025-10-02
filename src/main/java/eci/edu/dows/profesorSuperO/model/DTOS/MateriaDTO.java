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
public class MateriaDTO {
    private String id;
    private String nombre;
    private Boolean estado;
    private int  creditos;
}

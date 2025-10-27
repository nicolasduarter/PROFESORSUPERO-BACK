package eci.edu.dows.profesorSuperO.model.DTOS.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultadDTO {
    private String id;
    private String facultadName;
    private ArrayList<MateriaDTO> materias;
}

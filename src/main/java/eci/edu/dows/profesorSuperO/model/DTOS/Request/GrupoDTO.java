package eci.edu.dows.profesorSuperO.model.DTOS.Request;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.ProfesorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    private String id;
    private String nombre;
    private ProfesorDTO profesor;
    private int cupo;
    private MateriaDTO materia;
    private int cuposMax;
    private List<ClaseDTO> clases;
    private ArrayList<EstudianteDTO> estudiantes;
    private ArrayList<EstudianteDTO> listaEspera;


}

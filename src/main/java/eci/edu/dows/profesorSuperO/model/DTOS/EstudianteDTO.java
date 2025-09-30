package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Semaforo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO {
    private String usuario;
    private String clave;
    private String permiso;
    private String correo;
    private String id;
    private Facultad facultadObjeto;
    private int semestre;
    private Semaforo semaforo;


}

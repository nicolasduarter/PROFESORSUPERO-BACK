package eci.edu.dows.profesorSuperO.model.Usuarios;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Horario;
import eci.edu.dows.profesorSuperO.model.Semaforo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;



@Getter
@Setter
@NoArgsConstructor
@TypeAlias("Estudiante")
public class Estudiante extends Usuario {


    private int semestre;
    private ArrayList<Horario> horarios;
    @DBRef(lazy = false)
    private Facultad facultad;
    private String semaforoId;

    public Estudiante(String usuario, Permisos permiso, String correo, String Id, Facultad facultad, int semestre) {
        super(usuario, permiso, correo, Id);
        this.semestre = semestre;
        this.horarios = new ArrayList<>();
        this.facultad = facultad;
    }


}
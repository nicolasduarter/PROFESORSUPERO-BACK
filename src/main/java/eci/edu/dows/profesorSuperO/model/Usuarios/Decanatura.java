package eci.edu.dows.profesorSuperO.model.Usuarios;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Facultad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;


@Getter
@Setter
@NoArgsConstructor
@TypeAlias("Decanatura")
public class Decanatura extends Usuario {

    @DBRef
    private Facultad facultad;

    public Decanatura(String usuario, Permisos permiso, String correo, String id, Facultad facultad) {
        super(usuario, permiso, correo, id);
        this.facultad = facultad;
    }


}


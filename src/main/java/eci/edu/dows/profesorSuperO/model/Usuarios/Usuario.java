package eci.edu.dows.profesorSuperO.model.Usuarios;


import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Usuarios")
public abstract class Usuario {


    @Id
    private String id;

    @Indexed(unique = true)
    private String correo;
    private String fullName;
    @Transient
    private Permisos permiso;

    public Usuario(String fullName, Permisos permiso, String correo, String id) {
        this.id = id;
        this.fullName = fullName;
        this.permiso = permiso;
        this.correo = correo;
    }

    public Usuario() {}

}

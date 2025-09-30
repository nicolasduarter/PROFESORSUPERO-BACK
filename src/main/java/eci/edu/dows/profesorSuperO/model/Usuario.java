package eci.edu.dows.profesorSuperO.model;


import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public abstract class Usuario {


    @Id
    private String id;

    @Indexed(unique = true)
    private String correo;

    private String usuario;
    private String clave;
    @Transient
    private Permisos permiso;

    public Usuario(String usuario, String clave, Permisos permiso, String correo, String id) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.permiso = permiso;
        this.correo = correo;
    }

    public Usuario() {}

}

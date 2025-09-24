package eci.edu.dows.profesorSuperO.model;

public abstract class Usuario {
    private String id;
    private String usuario;
    private String correo;
    private String clave;
    private String permiso;

    public Usuario(String usuario, String clave, String permiso, String correo, String id) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.permiso = permiso;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
}

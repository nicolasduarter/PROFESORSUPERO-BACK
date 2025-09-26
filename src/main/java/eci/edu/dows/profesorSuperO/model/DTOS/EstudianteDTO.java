package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Semaforo;

public class EstudianteDTO {
    private String usuario;
    private String clave;
    private String permiso;
    private String correo;
    private String id;
    private Facultad facultadObjeto;
    private int semestre;
    private Semaforo semaforo;

    public EstudianteDTO(String usuario, String clave, String permiso, String correo, String id,
                         Facultad facultadObjeto, int semestre, Semaforo semaforo) {
        this.usuario = usuario;
        this.clave = clave;
        this.permiso = permiso;
        this.correo = correo;
        this.id = id;
        this.facultadObjeto = facultadObjeto;
        this.semestre = semestre;
        this.semaforo = semaforo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Facultad getFacultadObjeto() {
        return facultadObjeto;
    }

    public void setFacultadObjeto(Facultad facultadObjeto) {
        this.facultadObjeto = facultadObjeto;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }
}

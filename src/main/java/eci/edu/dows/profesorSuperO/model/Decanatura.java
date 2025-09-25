package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document("Decanatura")
public class Decanatura {

    @Id
    private String nombre;
    private Facultades facultad;
    private List<Solicitud> solicitudes;

    public Decanatura(String nombre, Facultades facultad, List<Solicitud> solicitudes) {
        this.nombre = nombre;
        this.facultad = facultad;
        this.solicitudes = solicitudes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}


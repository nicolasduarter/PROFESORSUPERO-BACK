package eci.edu.dows.profesorSuperO.model;

import java.util.List;


public class Decanatura {
    private String facultad;
    private List<Solicitud> solicitudes;

    public Decanatura(String facultad, List<Solicitud> solicitudes) {
        this.facultad = facultad;
        this.solicitudes = solicitudes;
    }

    public String getFacultad() {
        return facultad;
    }
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

}

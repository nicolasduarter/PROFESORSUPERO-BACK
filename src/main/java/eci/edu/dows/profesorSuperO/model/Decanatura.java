package eci.edu.dows.profesorSuperO.model;

import java.util.List;


public class Decanatura {
    private Facultades facultad;
    private List<Solicitud> solicitudes;

    public Decanatura(Facultades facultad, List<Solicitud> solicitudes) {
        this.facultad = facultad;
        this.solicitudes = solicitudes;
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

package eci.edu.dows.profesorSuperO.model;

import java.util.List;

public class Decanatura {
    private String facultad;
    private List<Solicitud> solicitudes;

    public Decanatura(String facultad, List<Solicitud> solicitudes) {
        this.facultad = facultad;
        this.solicitudes = solicitudes;
    }
}

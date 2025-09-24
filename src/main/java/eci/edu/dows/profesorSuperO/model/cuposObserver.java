package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;
import java.util.List;

public class cuposObserver implements GruposObserver {

    private List<String> mensajes = new ArrayList<>();

    @Override
    public void notificar(Grupo grupo) {
        String respuesta = "quedan " + grupo.getCupo() + " cupos para el grupo " + grupo.getNombre();
        mensajes.add(respuesta);
        System.out.println(respuesta);
    }

    public List<String> getMensajes() {
        return mensajes;
    }
}

package eci.edu.dows.profesorSuperO.model.Observer;

import eci.edu.dows.profesorSuperO.model.Grupo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class cuposObserver implements GruposObserver {

    private List<String> mensajes = new ArrayList<>();

    @Override
    public void notificar(Grupo grupo) {
        String respuesta = "quedan " + grupo.getCupo() + " cupos para el grupo " + grupo.getNombre();
        mensajes.add(respuesta);
        System.out.println(respuesta);
    }
}

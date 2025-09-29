package eci.edu.dows.profesorSuperO.model.Observer;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PeriodoHabilitadoObserver implements PeriodoObserver {

    private List<String> mensajes = new ArrayList<>();

    @Override
    public void notificarPeriodo( ) {
        String respuesta = "la disponibilidad del periodo para cambios se a modificado";
        mensajes.add(respuesta);
        System.out.println(respuesta);
    }


}

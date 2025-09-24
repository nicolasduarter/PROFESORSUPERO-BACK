package eci.edu.dows.profesorSuperO.service;
import eci.edu.dows.profesorSuperO.model.*;
public class GrupoService {
    public GrupoService() {
    }
    public Grupo addGrupo(String idGrupo, String nombre, Profesor profesor, String franjaHoraria, String salon,int cupo, String estado) {
        Grupo grupo = new Grupo( idGrupo,  nombre,  profesor,  franjaHoraria,  salon, cupo,  estado);
        return grupo;
    }
    public void modificarProfesor(Grupo grupo, Profesor profesor) {
        grupo.setProfesor(profesor);
    }
    public void eliminarGrupo(Grupo grupo) {

    }
}

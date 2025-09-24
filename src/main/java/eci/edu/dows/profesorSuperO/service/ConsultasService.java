package eci.edu.dows.profesorSuperO.service;
import eci.edu.dows.profesorSuperO.model.*;

import java.util.List;

public class ConsultasService {

    public ConsultasService() {
    }
    public Horario consultarHorarioEstudiante(Estudiante estudiante) {
        return estudiante.getHorarios().get(estudiante.getHorarios().size()-1);
    }
    public List<Horario> consultarHorariosEstudiante(Estudiante estudiante) {
        return estudiante.getHorarios();
    }
    public List<Solicitud> consultarSolicitudes(Estudiante estudiante) {
        return estudiante.getSolicitudes();
    }
    public Semaforo consultarSemaforoEstudiante(Estudiante estudiante) {
        return estudiante.getSemaforo();
    }
    public Profesor consultarProfesorGrupo(Grupo grupo) {
       return grupo.getProfesor();
    }
    public int consultarCuposGrupo(Grupo grupo) {
        return grupo.getCupo();
    }
    public List<Solicitud> consultarSolicitudesDecanatura(Decanatura decanatura) {
        return decanatura.getSolicitudes();
    }
}

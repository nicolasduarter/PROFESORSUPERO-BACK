package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemaforoService {

    private final MateriaEstudianteService materiaEstudianteService;

    @Autowired
    public SemaforoService(MateriaEstudianteService materiaEstudianteService) {
        this.materiaEstudianteService = materiaEstudianteService;
    }

    private Semaforo actualizarSemaforo(Estudiante estudiante){
        List<MateriaEstudiante> historial = materiaEstudianteService.obtenerHistorial(estudiante.getId());
        ArrayList<MateriaEstudiante> historialMaterias = new ArrayList<>(historial);
        Semaforo semaforo = estudiante.getSemaforo();
        semaforo.setMateriaEstudiante(historialMaterias);
        semaforo.setFacultad(estudiante.getFacultad());
        semaforo.setMateriasVistas(historial.size());

        int creditosTotales = this.obtenerCreditosTotales(estudiante.getFacultad());
        int creditosActuales = this.obtenerCreditosActuales(historialMaterias);
        int creditosFaltantes = this.obtenerCreditosFaltantes(creditosTotales, creditosActuales);
        semaforo.setCreditosFaltantes(creditosFaltantes);
        semaforo.setCreditosTotales(creditosTotales);
        semaforo.setCreditosActuales(creditosActuales);
        estudiante.setSemaforo(semaforo);
        return semaforo;
    }

    public Semaforo getSemaforo(Estudiante estudiante) {
        return  this.actualizarSemaforo(estudiante);

    }


    private int obtenerCreditosActuales(ArrayList<MateriaEstudiante> materiaEstudiante) {
        return materiaEstudiante.stream()
                .filter(me -> me.getEstado() == EstadoMateria.APROBADA)
                .mapToInt(me -> me.getMateria().getCreditos())
                .sum();
    }

    private int obtenerCreditosTotales(Facultad facultad) {
        return facultad.getMaterias().stream().mapToInt(Materia::getCreditos).sum();
    }

    private int obtenerCreditosFaltantes(int creditosTotales, int creditosActuales) {
        return creditosTotales - creditosActuales;
    }

}

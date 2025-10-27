package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Mappers.SemaforoMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.service.Interfaces.SemaforoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemaforoServiceImpl implements SemaforoService {

    private final MateriaEstudianteServiceImpl materiaEstudianteServiceImpl;
    private final SemaforoMapper semaforoMapper;

    @Autowired
    public SemaforoServiceImpl(MateriaEstudianteServiceImpl materiaEstudianteServiceImpl,
                               SemaforoMapper semaforoMapper) {
        this.materiaEstudianteServiceImpl = materiaEstudianteServiceImpl;
        this.semaforoMapper = semaforoMapper;
    }

    public SemaforoDTO crearSemaforo(SemaforoDTO dto, Estudiante estudiante) {
        Semaforo semaforo = semaforoMapper.toSemaforo(dto);
        semaforo.setFacultad(estudiante.getFacultad());
        estudiante.setSemaforo(semaforo);
        return semaforoMapper.toDTO(semaforo);
    }


    public SemaforoDTO getSemaforoDTO(Estudiante estudiante) {
        Semaforo semaforo = this.actualizarSemaforo(estudiante);
        return semaforoMapper.toDTO(semaforo);
    }


    private Semaforo actualizarSemaforo(Estudiante estudiante){
        List<MateriaEstudiante> historial = materiaEstudianteServiceImpl.obtenerHistorial(estudiante.getId());
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

    private int obtenerCreditosActuales(ArrayList<MateriaEstudiante> materiaEstudiante) {
        return materiaEstudiante.stream()
                .filter(me -> me.getEstado() == EstadoMateria.APROBADA)
                .mapToInt(me -> me.getMateria().getCreditos())
                .sum();
    }

    private int obtenerCreditosTotales(Facultad facultad) {
        return facultad.getMaterias().stream()
                .mapToInt(Materia::getCreditos)
                .sum();
    }

    private int obtenerCreditosFaltantes(int creditosTotales, int creditosActuales) {
        return creditosTotales - creditosActuales;
    }
}

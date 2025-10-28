package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.SemaforoMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.repository.FacultadRepository;
import eci.edu.dows.profesorSuperO.repository.SemaforoRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.SemaforoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemaforoServiceImpl implements SemaforoService {

    private final MateriaEstudianteServiceImpl materiaEstudianteServiceImpl;
    private final SemaforoMapper semaforoMapper;
    private final SemaforoRepository semaforoRepository;
    private final FacultadRepository facultadRepository;

    @Autowired
    public SemaforoServiceImpl(MateriaEstudianteServiceImpl materiaEstudianteServiceImpl,
                               SemaforoMapper semaforoMapper, SemaforoRepository semaforoRepository, FacultadRepository facultadRepository) {
        this.materiaEstudianteServiceImpl = materiaEstudianteServiceImpl;
        this.semaforoMapper = semaforoMapper;
        this.semaforoRepository = semaforoRepository;
        this.facultadRepository = facultadRepository;
    }

    public void crearSemaforo(Estudiante estudiante) {
        Semaforo semaforo = new Semaforo();
        semaforo.setFacultadId(estudiante.getFacultad().getId());
        semaforoRepository.save(semaforo);
        estudiante.setSemaforoId(semaforo.getId());
    }


    public SemaforoDTO getSemaforoDTO(Estudiante estudiante) {
        Semaforo semaforo = this.actualizarSemaforo(estudiante);
        return semaforoMapper.toDTO(semaforo);
    }


    private Semaforo actualizarSemaforo(Estudiante estudiante) {
        List<MateriaEstudiante> historial = materiaEstudianteServiceImpl.obtenerHistorial(estudiante.getId());
        ArrayList<MateriaEstudiante> historialMaterias = new ArrayList<>(historial);

        Semaforo semaforo = null;
        if (estudiante.getSemaforoId() != null) {
            semaforo = semaforoRepository.findById(estudiante.getSemaforoId())
                    .orElseThrow(() -> new NotFoundException("Semáforo no encontrado"));
        } else {
            semaforo = new Semaforo();
            semaforo = semaforoRepository.save(semaforo);
            estudiante.setSemaforoId(semaforo.getId());
        }

        semaforo.setMateriaEstudiante(historialMaterias);
        semaforo.setFacultadId(estudiante.getFacultad().getId());

        generarAtributos(semaforo);

        semaforoRepository.save(semaforo);
        return semaforo;
    }


    private void generarAtributos(Semaforo semaforo) {

        Facultad facultad = facultadRepository.findById(semaforo.getFacultadId())
                .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));

        int creditosTotales = facultad.getMaterias() != null
                ? facultad.getMaterias().stream()
                .mapToInt(Materia::getCreditos)
                .sum()
                : 0;



        int creditosActuales = semaforo.getMateriaEstudiante() != null
                ? semaforo.getMateriaEstudiante().stream()
                .filter(m -> m.getEstado() == EstadoMateria.APROBADA)
                .mapToInt(m -> m.getMateria().getCreditos())
                .sum()
                : 0;


        int materiasVistas = semaforo.getMateriaEstudiante() != null
                ? (int) semaforo.getMateriaEstudiante().stream()
                .filter(m -> m.getEstado() != EstadoMateria.PENDIENTE)
                .count()
                : 0;


        double promedioTemp = semaforo.getMateriaEstudiante() != null
                ? semaforo.getMateriaEstudiante().stream()
                .filter(m -> m.getNota() > 0)
                .mapToDouble(MateriaEstudiante::getNota)
                .average()
                .orElse(0.0)
                : 0.0;

        int creditosFaltantes = Math.max(0, creditosTotales - creditosActuales);

        semaforo.setCreditosTotales(creditosTotales);
        semaforo.setCreditosActuales(creditosActuales);
        semaforo.setCreditosFaltantes(creditosFaltantes);
        semaforo.setMateriasVistas(materiasVistas);
        semaforo.setPromedio((int) Math.round(promedioTemp));
    }

}

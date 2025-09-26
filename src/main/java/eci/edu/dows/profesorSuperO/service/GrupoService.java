package eci.edu.dows.profesorSuperO.service;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import io.micrometer.observation.Observation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;


    public GrupoService(EstudianteRepository estudianteRepository,
                        GrupoRepository grupoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;

    }

    public Grupo crearGrupo(GrupoDTO dto) {
        Grupo grupo = new Grupo(dto.getIdGrupo(),
                dto.getNombre(),
                dto.getProfesor(),
                dto.getCupo(),
                dto.getMateria(),
                dto.getCuposMax());


        return grupoRepository.save(grupo);
    }

    public void eliminarGrupo(String id) {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupoRepository.delete(grupo);
    }

    public Grupo modificarCuposGrupo(String grupoId, int cupo) {
        Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupo.setCupo(cupo);

        return grupoRepository.save(grupo);
    }

    public Grupo agregarEstudianteAGrupo(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (grupo.getCupo() <= 0) {
            throw new RuntimeException("El grupo ya está lleno");
        }
        grupo.getEstudiantes().add(estudiante);
        grupo.setCupo(grupo.getCupo() + 1);
        for (GruposObserver observador : grupo.getObservadores()) {
            observador.notificar(grupo);
        }
        return grupoRepository.save(grupo);
    }

    public Grupo eliminarEstudianteAGrupo(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        grupo.getEstudiantes().remove(estudiante);
        grupo.setCupo(grupo.getCupo() + 1);

        return grupoRepository.save(grupo);
    }
}

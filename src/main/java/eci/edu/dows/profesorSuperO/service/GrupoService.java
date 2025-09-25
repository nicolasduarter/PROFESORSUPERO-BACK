package eci.edu.dows.profesorSuperO.service;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.repository.*;
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

    public Grupo crearGrupo(String idGrupo, String nombre, Profesor profesor,
                            String franjaHoraria, String salon, int cupo, String estado) {
        Grupo grupo = new Grupo(idGrupo, nombre, profesor, franjaHoraria, salon, cupo, estado);

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

    public Grupo modificarHorarioGrupo(String grupoId,String horario) {
        Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupo.setFranjaHoraria(horario);

        return grupoRepository.save(grupo);
    }

    public Grupo modificarSalonGrupo(String grupoId,String salon) {
        Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupo.setSalon(salon);

        return grupoRepository.save(grupo);
    }

    public Grupo agregarEstudianteAGrupo(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        grupo.getEstudiantes().add(estudiante);
        grupo.setCupo(grupo.getCupo() -1);
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

package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.GrupoMapper;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Observer.GruposObserver;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.GrupoRepository;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    public GrupoService(EstudianteRepository estudianteRepository,
                        GrupoRepository grupoRepository,
                        GrupoMapper grupoMapper) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
    }

    public GrupoDTO crearGrupo(GrupoDTO dto) {
        Grupo grupo = grupoMapper.toGrupo(dto);
        Grupo grupoGuardado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoGuardado);
    }

    public void eliminarGrupo(String id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupoRepository.delete(grupo);
    }

    public GrupoDTO modificarCuposGrupo(String grupoId, int cupo) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        grupo.setCupo(cupo);
        Grupo grupoActualizado = grupoRepository.save(grupo);

        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO agregarEstudianteAGrupo(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (grupo.getCupo() <= 0) {
            throw new RuntimeException("El grupo ya está lleno");
        }

        grupo.getEstudiantes().add(estudiante);
        grupo.setCupo(grupo.getCupo() - 1);

        for (GruposObserver observador : grupo.getObservadores()) {
            observador.notificar(grupo);
        }

        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO eliminarEstudianteAGrupo(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        grupo.getEstudiantes().remove(estudiante);
        grupo.setCupo(grupo.getCupo() + 1);

        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }
}

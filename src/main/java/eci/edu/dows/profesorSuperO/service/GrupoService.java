package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.ClaseMapper;
import eci.edu.dows.profesorSuperO.Util.GrupoMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Observer.GruposObserver;
import eci.edu.dows.profesorSuperO.model.Profesor;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.GrupoRepository;
import eci.edu.dows.profesorSuperO.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;
    private final ProfesorRepository profesorRepository;
    private final ClaseMapper claseMapper;

    public GrupoService(EstudianteRepository estudianteRepository,
                        GrupoRepository grupoRepository,
                        GrupoMapper grupoMapper,ProfesorRepository profesorRepository,ClaseMapper claseMapper) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
        this.profesorRepository = profesorRepository;
        this.claseMapper = claseMapper;
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

        if (grupo.getEstudiantes().contains(estudiante)) {
            throw new RuntimeException("El estudiante ya está en el grupo");
        }
        if (grupo.getListaEspera().contains(estudiante)) {
            throw new RuntimeException("El estudiante ya está en la lista de espera");
        }

        if (grupo.getCupo() > 0) {
            grupo.getEstudiantes().add(estudiante);
            grupo.setCupo(grupo.getCupo() - 1);
        } else {
            grupo.getListaEspera().add(estudiante);
        }

        for (GruposObserver observador : grupo.getObservadores()) {
            observador.notificar(grupo);
        }
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public List<Estudiante> consultarListaEspera(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        return grupo.getListaEspera();
    }

    public GrupoDTO eliminarEstudianteAGrupo(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        boolean estudianteRemovido = grupo.getEstudiantes().removeIf(est -> est.getId().equals(estudianteId));
        if (!estudianteRemovido) {
            throw new RuntimeException("Estudiante no encontrado en el grupo");
        }
        grupo.setCupo(grupo.getCupo() + 1);
        if (!grupo.getListaEspera().isEmpty()) {
            Estudiante primerEnEspera = grupo.getListaEspera().remove(0);
            grupo.getEstudiantes().add(primerEnEspera);
            grupo.setCupo(grupo.getCupo() - 1);
        }

        for (GruposObserver observador : grupo.getObservadores()) {
            observador.notificar(grupo);
        }
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO eliminarDeListaEspera(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        boolean removido = grupo.getListaEspera().removeIf(estudiante ->
                estudiante.getId().equals(estudianteId));
        if (!removido) {
            throw new RuntimeException("Estudiante no encontrado en lista de espera");
        }
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO agregarProfesorAGrupo(String grupoId, String profesorId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Profesor profe = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        grupo.setProfesor(profe);
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO eliminarProfesorAGrupo(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        grupo.setProfesor(null);
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO agregarClasesAGrupo(String grupoId, List<ClaseDTO> clases) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupo.getClases().addAll(clases.stream().map(claseMapper::toClass).toList());

        Grupo grupoActualizado = grupoRepository.save(grupo);
        return  grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO eliminarClasesAGrupo(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupo.setClases(null);
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO cambiarNombreAGrupo(String grupoId, String nombre) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupo.setNombre(nombre);
        Grupo grupoActualizado = grupoRepository.save(grupo);
        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO updateMaximumCapacity(String grupoId, int cupo) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        grupo.setCuposMax(cupo);
        Grupo grupoActualizado = grupoRepository.save(grupo);

        return grupoMapper.toDTO(grupoActualizado);
    }

    public GrupoDTO getMaximumCapacity(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        return  grupoMapper.toDTO(grupo);
    }


    public GrupoDTO getActualCapacity(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        return  grupoMapper.toDTO(grupo);
    }


    public GrupoDTO deleteStudentOfGroup(String grupoId, String estudianteId) {
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

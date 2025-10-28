package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.ClaseMapper;
import eci.edu.dows.profesorSuperO.Util.Mappers.GrupoMapper;
import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO2;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.ClaseDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Horario;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Observer.GruposObserver;
import eci.edu.dows.profesorSuperO.model.Usuarios.Profesor;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.GrupoRepository;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import eci.edu.dows.profesorSuperO.repository.ProfesorRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;
    private final ProfesorRepository profesorRepository;
    private final ClaseMapper claseMapper;
    private final MateriaRepository materiaRepository;

    @Autowired
    public GrupoServiceImpl(EstudianteRepository estudianteRepository,
                            GrupoRepository grupoRepository,
                            GrupoMapper grupoMapper, MateriaRepository materiaRepository, ProfesorRepository profesorRepository, ClaseMapper claseMapper) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
        this.profesorRepository = profesorRepository;
        this.claseMapper = claseMapper;
        this.materiaRepository = materiaRepository;
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


    public int getActualCapacity(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        int cupos = grupo.getCupo();
        return  cupos;
    }


    public GrupoDTO deleteStudentOfGroup(String grupoId, String estudianteId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new NotFoundException("Grupo no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        boolean removed = grupo.getEstudiantes().removeIf(e -> e.getId().equals(estudianteId));

        if (removed) {
            grupo.setCupo(grupo.getCupo() + 1);
            Grupo grupoActualizado = grupoRepository.save(grupo);
            return grupoMapper.toDTO(grupoActualizado);
        } else {
            throw new RuntimeException("El estudiante no estaba en el grupo");
        }
    }

    public GrupoDTO2 getMaximumCapacity2(String grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));


        GrupoDTO2 dto = new GrupoDTO2();
        dto.setId(grupo.getId());
        dto.setNombre(grupo.getNombre());
        dto.setCupo(grupo.getCupo());
        dto.setCuposMax(grupo.getCuposMax());

        if(grupo.getProfesor()!= null){
            dto.setProfesorId(grupo.getProfesor().getId());
        }
        if(grupo.getMateria()!= null){
            dto.setMateriaId(grupo.getMateria().getId());
        }

        if (grupo.getEstudiantes() != null) {
            List<String> estudianteIds = grupo.getEstudiantes()
                    .stream()
                    .map(Estudiante::getId)
                    .toList();
            dto.setEstudianteIds(estudianteIds);
        }

        return dto;
    }



    public GrupoDTO asignarMateriaAGrupo(String grupoId, String materiaId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new NotFoundException("Grupo no encontrado"));

        Materia materia = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada"));

        grupo.setMateria(materia);
        grupoRepository.save(grupo);

        return grupoMapper.toDTO(grupo);
    }


    public GrupoDTO asignarMateriaYGrupo(String idEstudiante, String grupoId) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado."));

        if (grupo.getCupo() <= 0) {
            throw new RuntimeException("El grupo no tiene cupo disponible.");
        }

        Horario horario;
        if (estudiante.getHorarios().isEmpty()) {
            horario = new Horario();
            horario.setGrupos(new ArrayList<>());
            estudiante.getHorarios().add(horario);
        } else {
            horario = estudiante.getHorarios().get(estudiante.getHorarios().size() - 1);
        }

        for (Grupo gExistente : horario.getGrupos()) {
            for (Clase cExistente : gExistente.getClases()) {
                for (Clase cNuevo : grupo.getClases()) {
                    if (cExistente.getDiaSemana() == cNuevo.getDiaSemana() &&
                            cNuevo.getHoraInicio().isBefore(cExistente.getHoraFin()) &&
                            cNuevo.getHoraFin().isAfter(cExistente.getHoraInicio())) {
                        throw new RuntimeException("El grupo tiene conflicto con otro horario");
                    }
                }
            }
        }

        horario.getGrupos().add(grupo);
        grupo.setCupo(grupo.getCupo() - 1);

        System.out.println(estudiante.getHorarios().size());
        estudianteRepository.save(estudiante);
        System.out.println(estudiante.getHorarios().size());
        return grupoMapper.toDTO(grupoRepository.save(grupo));
    }


}

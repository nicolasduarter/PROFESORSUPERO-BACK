package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoMateria;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.GrupoRepository;
import eci.edu.dows.profesorSuperO.repository.MateriaEstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaEstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoService grupoService;

    private final MateriaEstudianteRepository materiaEstudianteRepository;

    @Autowired
    public MateriaEstudianteService(MateriaEstudianteRepository materiaEstudianteRepository) {
        this.materiaEstudianteRepository = materiaEstudianteRepository;
    }

    public MateriaEstudiante crearInscripcionMateria(Estudiante estudiante, Materia materia) {
        Optional<MateriaEstudiante> existente = materiaEstudianteRepository
                .findByEstudiante_IdAndMateria_Id(estudiante.getId(), materia.getId());

        if (existente.isPresent()) {
            throw new IllegalStateException("el estudiante ya esta inscrito ");
        }

        MateriaEstudiante m = new MateriaEstudiante();
        m.setMateria(materia);
        m.setEstudiante(estudiante);
        m.setEstado(EstadoMateria.EN_CURSO);
        m.setIntentos(1);
        m.setNota(0.0);
        m.setFechaAprobada(null);

        return materiaEstudianteRepository.save(m);
    }


    public MateriaEstudiante aprobarMateria(String idEstudiante, String idMateria, double nota) {
        MateriaEstudiante me = materiaEstudianteRepository
                .findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria)
                .orElseThrow(() -> new RuntimeException("no encontrado"));

        me.setEstado(EstadoMateria.APROBADA);
        me.setNota(nota);
        me.setFechaAprobada(LocalDate.now());

        return materiaEstudianteRepository.save(me);
    }

    public MateriaEstudiante cancelarMateria(String idEstudiante, String idMateria) {
        MateriaEstudiante me = materiaEstudianteRepository
                .findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria)
                .orElseThrow(() -> new RuntimeException("no encontrado"));

        me.setEstado(EstadoMateria.CANCELADA);
        return materiaEstudianteRepository.save(me);
    }

    public MateriaEstudiante repobrarMateria(String idEstudiante, String idMateria, double nota) {
        MateriaEstudiante me = materiaEstudianteRepository
                .findByEstudiante_IdAndMateria_Id(idEstudiante, idMateria)
                .orElseThrow(() -> new RuntimeException("no encontrado"));

        me.setEstado(EstadoMateria.REPROBADA);
        me.setNota(nota);
        me.setIntentos(me.getIntentos()+1);
        return materiaEstudianteRepository.save(me);
    }

    public List<MateriaEstudiante> obtenerHistorial(String idEstudiante) {
        return materiaEstudianteRepository.findByEstudiante_Id(idEstudiante);
    }

    public MateriaEstudiante asignarMateriaYGrupo(String idEstudiante, String idMateria) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));

        Materia materia = materiaRepository.findById(idMateria)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada."));

        Grupo grupoDisponible = grupoRepository.findAll().stream()
                .filter(g -> g.getMateria() != null &&
                        g.getMateria().getId().equals(idMateria) &&
                        g.getCupo() > 0)
                .findAny().orElseThrow(() -> new RuntimeException("No hay grupos disponibles para esta materia."));

        MateriaEstudiante materiaEstudiante = crearInscripcionMateria(estudiante, materia);
        grupoService.agregarEstudianteAGrupo(grupoDisponible.getIdGrupo(), idEstudiante);

        return materiaEstudiante;
    }
}

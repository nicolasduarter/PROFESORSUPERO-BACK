package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultasService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final SolicitudRepository solicitudRepository;
    private final SemaforoService  semaforoService;

    public ConsultasService(EstudianteRepository estudianteRepository,
                            GrupoRepository grupoRepository,
                            SolicitudRepository solicitudRepository, SemaforoService semaforoService) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
        this.solicitudRepository = solicitudRepository;
        this.semaforoService = semaforoService;
    }


    public Horario consultarUltimoHorarioEstudiante(String estudianteId) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(estudianteId);
        if (estudiante.isPresent()&& !estudiante.get().getHorarios().isEmpty()) {
            List<Horario> horarios = estudiante.get().getHorarios();
            return horarios.get(horarios.size() - 1);
        }
        return null;
    }

    public List<Horario> consultarHorariosEstudiante(String estudianteId) {
        return estudianteRepository.findById(estudianteId)
                .map(Estudiante::getHorarios)
                .orElse(null);
    }

    public List<Solicitud> consultarSolicitudesEstudiante(String estudianteId) {
        return solicitudRepository.findByEstudianteId(estudianteId);
    }

    public Semaforo consultarSemaforoEstudiante(String estudianteId) {
        Estudiante e = estudianteRepository.findById(estudianteId).orElseThrow(() -> new NotFoundException("no se encontro estudiante con esa ID"))


        return estudianteRepository.findById(estudianteId)
                .map(Estudiante::getSemaforo)
                .orElse(null);
    }

    public Profesor consultarProfesorGrupo(String grupoId) {
        return grupoRepository.findById(grupoId)
                .map(Grupo::getProfesor)
                .orElse(null);
    }

    public int consultarCuposGrupo(String grupoId) {
        return grupoRepository.findById(grupoId)
                .map(Grupo::getCupo)
                .orElse(0);
    }

    public Grupo obtenerGrupo(String id) {
        return grupoRepository.findById(id).orElse(null);
    }

}


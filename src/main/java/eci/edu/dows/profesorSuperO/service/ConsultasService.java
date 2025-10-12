package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.GrupoMapper;
import eci.edu.dows.profesorSuperO.Util.HorarioMapper;
import eci.edu.dows.profesorSuperO.Util.ProfesorMapper;
import eci.edu.dows.profesorSuperO.Util.SolicitudMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.HorarioDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.repository.*;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultasService {

    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;
    private final SolicitudRepository solicitudRepository;
    private final SemaforoService  semaforoService;
    private final HorarioMapper  horarioMapper;
    private final SolicitudMapper solicitudMapper;
    private final ProfesorMapper profesorMapper;
    private final GrupoMapper grupoMapper;

    public ConsultasService(EstudianteRepository estudianteRepository,
                            GrupoRepository grupoRepository,
                            SolicitudRepository solicitudRepository,
                            SemaforoService semaforoService,
                            HorarioMapper horarioMapper,
                            SolicitudMapper solicitudMapper,ProfesorMapper profesorMapper,
                            GrupoMapper grupoMapper) {
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
        this.solicitudRepository = solicitudRepository;
        this.semaforoService = semaforoService;
        this.horarioMapper = horarioMapper;
        this.solicitudMapper = solicitudMapper;
        this.grupoMapper = grupoMapper;
        this.profesorMapper = profesorMapper;
    }


    public HorarioDTO consultarUltimoHorarioEstudiante(String estudianteId) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(estudianteId);
        if (estudiante.isPresent()&& !estudiante.get().getHorarios().isEmpty()) {
            List<Horario> horarios = estudiante.get().getHorarios();
            Horario h = horarios.get(horarios.size() - 1);
            return horarioMapper.toDTO(h);
        }
        return null;
    }

    public List<HorarioDTO> consultarHorariosEstudiante(String estudianteId) {
        return estudianteRepository.findById(estudianteId)
                .map(Estudiante::getHorarios)
                .stream()
                .flatMap(List::stream)
                .map(horarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SolicitudDTO consultarSolicitudPorEstudiante(String idStudent,String idSolicitud) {
        List<Solicitud> s = solicitudRepository.findByEstudianteId(idStudent).stream().toList();
        Solicitud solicitud = s.stream().filter(soli-> soli.getId().equals(idSolicitud)).findFirst().orElse(null);
        return  solicitudMapper.toDTO(solicitud);
    }
    public List<SolicitudDTO> consultarSolicitudesEstudiante(String estudianteId) {
        return solicitudRepository.findByEstudianteId(estudianteId).stream().map(solicitudMapper::toDTO).collect(Collectors.toList());
    }

    public SemaforoDTO consultarSemaforoEstudiante(String estudianteId) {
        Estudiante e = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NotFoundException("no se encontro estudiante con esa ID"));

        return semaforoService.getSemaforoDTO(e);
    }

    public Optional<ProfesorDTO> consultarProfesorGrupo(String grupoId) {
        return grupoRepository.findById(grupoId)
                .map(Grupo::getProfesor)
                .map(profesorMapper::toDTO);
    }

    public int consultarCuposGrupo(String grupoId) {
        return grupoRepository.findById(grupoId)
                .map(Grupo::getCupo)
                .orElse(0);
    }

    public GrupoDTO obtenerGrupo(String id) {
        Grupo g =  grupoRepository.findById(id).orElseThrow(() -> new NotFoundException("no se encontro grupo"));
        return grupoMapper.toDTO(g);
    }

}


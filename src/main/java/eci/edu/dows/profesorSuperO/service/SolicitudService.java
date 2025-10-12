package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.SolicitudCambioGrupoMapper;
import eci.edu.dows.profesorSuperO.Util.SolicitudCambioMateriaMapper;
import eci.edu.dows.profesorSuperO.Util.SolicitudMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.repository.*;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final DecanaturaRepository decanaturaRepository;
    private final EstudianteRepository estudianteRepository;
    private final Validator validator;
    private CalendarioRepository calendarioRepository;
    private MateriaRepository materiaRepository;
    private GrupoRepository grupoRepository;
    private final SolicitudCambioGrupoMapper SolicitudCambioGrupoMapper;
    private final SolicitudCambioMateriaMapper solicitudCambioMateriaMapper;
    private final SolicitudMapper solicitudMapper;
    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository,
                            DecanaturaRepository decanaturaRepository,
                            EstudianteRepository estudianteRepository,
                            Validator validator,SolicitudCambioGrupoMapper solicitudCambioGrupoMapper,
                            SolicitudCambioMateriaMapper solicitudCambioMateriaMapper,SolicitudMapper solicitudMapper) {
        this.solicitudRepository = solicitudRepository;
        this.decanaturaRepository = decanaturaRepository;
        this.estudianteRepository = estudianteRepository;
        this.validator = validator;
        this.SolicitudCambioGrupoMapper = solicitudCambioGrupoMapper;
        this.solicitudCambioMateriaMapper = solicitudCambioMateriaMapper;
        this.solicitudMapper = solicitudMapper;
    }

    public SolicitudCambioGrupoDTO crearSolicitudCambioGrupo(SolicitudCambioGrupoDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudiante().getId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Materia materia = materiaRepository.findById(dto.getMateriaProblema().getId())
                .orElseThrow(() -> new NotFoundException("Materia no encontrada"));
        Grupo grupo = grupoRepository.findById(dto.getGrupo().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo actual no encontrado"));

        Grupo grupoCambio = grupoRepository.findById(dto.getGrupoCambio().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo de cambio no encontrado"));

        SolicitudCambioGrupo solicitud = SolicitudCambioGrupoMapper.toEntity(dto);
        solicitud.setEstudiante(estudiante);
        solicitud.setMateriaProblema(materia);
        solicitud.setGrupo(grupo);
        solicitud.setGrupoCambio(grupoCambio);

        return  SolicitudCambioGrupoMapper.toDTO(solicitudRepository.save(solicitud));

    }

    public SolicitudCambioMateriaDTO crearSolicitudCambioMateria(SolicitudCambioMateriaDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudiante().getId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Materia materiaProblema = materiaRepository.findById(dto.getMateriaProblema().getId())
                .orElseThrow(() -> new NotFoundException("Materia no encontrada"));

        Materia materiaNueva = materiaRepository.findById(dto.getMateriaCambio().getId()).
                orElseThrow(() -> new NotFoundException("Materia no encontrada"));
        Grupo grupo = grupoRepository.findById(dto.getGrupo().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo actual no encontrado"));

        Grupo grupoCambio = grupoRepository.findById(dto.getGrupoCambio().getIdGrupo())
                .orElseThrow(() -> new NotFoundException("Grupo de cambio no encontrado"));

        SolicitudCambioMateria solicitud = solicitudCambioMateriaMapper.toEntity(dto);
        solicitud.setEstudiante(estudiante);
        solicitud.setMateriaProblema(materiaProblema);
        solicitud.setGrupo(grupo);
        solicitud.setGrupoCambio(grupoCambio);
        solicitud.setMateriaCambio(materiaNueva);



        return solicitudCambioMateriaMapper.toDTO(solicitudRepository.save(solicitud));
    }

    public List<SolicitudDTO> consultarSolicitudes() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        return solicitudes.stream().map(solicitudMapper::toDTO).collect(Collectors.toList());
    }

    public SolicitudDTO consultarSolicitudPorId(String id) {
        Solicitud s = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        return solicitudMapper.toDTO(s);
    }




    public SolicitudDTO actualizarEstadoSolicitud(String id, EstadoSolicitud nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.setEstado(nuevoEstado);

        return solicitudMapper.toDTO(solicitudRepository.save(solicitud));
    }


    public void eliminarSolicitud(String id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitudRepository.delete(solicitud);
    }
}


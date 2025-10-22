package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudCambioGrupoMapper;
import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudCambioMateriaMapper;
import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudMapper;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.HistorialDecisionDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.repository.*;
import eci.edu.dows.profesorSuperO.service.Interfaces.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final DecanaturaRepository decanaturaRepository;
    private final EstudianteRepository estudianteRepository;
    private final HistorialDecisionRepository historialDecisionRepository;
    private final Validator validator;
    private final FacultadRepository facultadRepository;
    private CalendarioRepository calendarioRepository;
    private MateriaRepository materiaRepository;
    private GrupoRepository grupoRepository;
    private final SolicitudCambioGrupoMapper SolicitudCambioGrupoMapper;
    private final SolicitudCambioMateriaMapper solicitudCambioMateriaMapper;
    private final SolicitudMapper solicitudMapper;
    @Autowired
    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                DecanaturaRepository decanaturaRepository,
                                EstudianteRepository estudianteRepository,
                                Validator validator, SolicitudCambioGrupoMapper solicitudCambioGrupoMapper,
                                SolicitudCambioMateriaMapper solicitudCambioMateriaMapper,
                                SolicitudMapper solicitudMapper,
                                HistorialDecisionRepository historialDecisionRepository, FacultadRepository facultadRepository) {
        this.solicitudRepository = solicitudRepository;
        this.decanaturaRepository = decanaturaRepository;
        this.estudianteRepository = estudianteRepository;
        this.validator = validator;
        this.SolicitudCambioGrupoMapper = solicitudCambioGrupoMapper;
        this.solicitudCambioMateriaMapper = solicitudCambioMateriaMapper;
        this.solicitudMapper = solicitudMapper;
        this.historialDecisionRepository = historialDecisionRepository;
        this.facultadRepository = facultadRepository;
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

    public List<HistorialDecisionDTO> consultarHistorialDecisiones(String solicitudId) {
        List<HistorialDecision> historial = historialDecisionRepository
                .findBySolicitudIdOrderByFechaDecisionDesc(solicitudId);

        return historial.stream()
                .map(this::convertirHistorialADTO)
                .collect(Collectors.toList());
    }


    public SolicitudDTO actualizarEstadoSolicitud(String id, EstadoSolicitud nuevoEstado, String comentario, String usuario) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        EstadoSolicitud estadoAnterior = solicitud.getEstado();
        if (!estadoAnterior.equals(nuevoEstado)) {
            registrarDecisionEnHistorial(solicitud, estadoAnterior, nuevoEstado, comentario, usuario);
        }
        solicitud.setEstado(nuevoEstado);
        return solicitudMapper.toDTO(solicitudRepository.save(solicitud));
    }

    private void registrarDecisionEnHistorial(Solicitud solicitud, EstadoSolicitud estadoAnterior,
                                              EstadoSolicitud estadoNuevo, String comentario, String usuario) {
        HistorialDecision decision = new HistorialDecision(solicitud, estadoAnterior, estadoNuevo, comentario, usuario);
        historialDecisionRepository.save(decision);
    }

    private HistorialDecisionDTO convertirHistorialADTO(HistorialDecision historial) {
        return new HistorialDecisionDTO(
                historial.getId(),
                historial.getEstadoAnterior(),
                historial.getEstadoNuevo(),
                historial.getComentario(),
                historial.getUsuario(),
                historial.getFechaDecision()
        );
    }

    public void eliminarSolicitud(String id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitudRepository.delete(solicitud);
    }


    public SolicitudDTO agregarInformacionAdicional(String solicitudId, String texto) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getEstado() != EstadoSolicitud.INFORMACION_ADICIONAL) {
            throw new RuntimeException("La solicitud no está en estado de información adicional");
        }

        solicitud.setInfoAdicionalEstudiante(texto);
        solicitud.setEstado(EstadoSolicitud.PENDIENTE); // vuelve a pendiente para revisión

        return solicitudMapper.toDTO(solicitudRepository.save(solicitud));
    }



}


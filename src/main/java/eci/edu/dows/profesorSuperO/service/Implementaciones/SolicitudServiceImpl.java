package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudCambioGrupoMapper;
import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudCambioMateriaMapper;
import eci.edu.dows.profesorSuperO.Util.Mappers.SolicitudMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.SoliGrupoTestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SoliMateriaTestDTO;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.HistorialDecisionDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Enums.TipoSolicitud;
import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioGrupo;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioMateria;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.repository.*;
import eci.edu.dows.profesorSuperO.service.Interfaces.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;

import java.time.LocalDate;
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
    private final  GrupoRepository grupoRepository;
    private final SolicitudCambioGrupoMapper SolicitudCambioGrupoMapper;
    private final SolicitudCambioMateriaMapper solicitudCambioMateriaMapper;
    private final SolicitudMapper solicitudMapper;

    @Autowired
    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                DecanaturaRepository decanaturaRepository,
                                EstudianteRepository estudianteRepository,
                                Validator validator,
                                SolicitudCambioGrupoMapper solicitudCambioGrupoMapper,
                                SolicitudCambioMateriaMapper solicitudCambioMateriaMapper,
                                SolicitudMapper solicitudMapper,
                                HistorialDecisionRepository historialDecisionRepository,
                                FacultadRepository facultadRepository,
                                MateriaRepository materiaRepository,
                                GrupoRepository grupoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.decanaturaRepository = decanaturaRepository;
        this.estudianteRepository = estudianteRepository;
        this.validator = validator;
        this.SolicitudCambioGrupoMapper = solicitudCambioGrupoMapper;
        this.solicitudCambioMateriaMapper = solicitudCambioMateriaMapper;
        this.solicitudMapper = solicitudMapper;
        this.historialDecisionRepository = historialDecisionRepository;
        this.facultadRepository = facultadRepository;
        this.materiaRepository = materiaRepository;
        this.grupoRepository = grupoRepository;
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





    public SolicitudCambioMateriaDTO crearSolicitudCambioMateria(SoliMateriaTestDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Facultad f = estudiante.getFacultad();




        SolicitudCambioMateria solicitud = new SolicitudCambioMateria();
        solicitud.setEstudianteId(estudiante.getId());
        solicitud.setFacultadId(f.getId());
        solicitud.setMateriaProblemaId(dto.getMateriaProblemaId());
        solicitud.setMateriaCambioId(dto.getMateriaCambioId());
        solicitud.setGrupoId(dto.getGrupoId());
        solicitud.setGrupoCambioId(dto.getGrupoCambioId());
        solicitud.setMotivo(dto.getMotivo());
        solicitud.setPrioridad(dto.getPrioridad());
        solicitud.setTipoSolicitud(TipoSolicitud.CAMBIO_MATERIA);
        solicitud.setInfoAdicionalEstudiante(dto.getInfoAdicionalEstudiante());
        solicitud.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);

        return solicitudCambioMateriaMapper.toDTO(solicitudRepository.save(solicitud));
    }

    public SolicitudCambioGrupoDTO crearSolicitudCambioGrupo(SoliGrupoTestDTO dto) {

        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Materia materia = materiaRepository.findById(dto.getMateriaProblemaId())
                .orElseThrow(() -> new NotFoundException("Materia no encontrada"));


        String facultadId = estudiante.getFacultad().getId();

        SolicitudCambioGrupo solicitud = new SolicitudCambioGrupo(
                dto.getId(),
                dto.getEstudianteId(),
                facultadId,
                dto.getMotivo(),
                dto.getFecha() != null ? dto.getFecha() : LocalDate.now(),
                dto.getMateriaProblemaId(),
                dto.getGrupoId(),
                dto.getGrupoCambioId()
        );

        return SolicitudCambioGrupoMapper.toDTO(solicitudRepository.save(solicitud));
    }
}


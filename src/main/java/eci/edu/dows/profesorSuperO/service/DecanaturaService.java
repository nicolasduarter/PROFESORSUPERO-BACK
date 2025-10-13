package eci.edu.dows.profesorSuperO.service;


import eci.edu.dows.profesorSuperO.Util.CalendarioAcademicoMapper;
import eci.edu.dows.profesorSuperO.Util.EstudianteMapper;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.FacultadMapper;
import eci.edu.dows.profesorSuperO.Util.SolicitudMapper;
import eci.edu.dows.profesorSuperO.model.CalendarioAcademico;
import eci.edu.dows.profesorSuperO.model.DTOS.CalendarioAcademicoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.repository.CalendarioRepository;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudCommand;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudFactory;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecanaturaService {
    private final SolicitudRepository solicitudRepository;
    private final AccionSolicitudFactory accionSolicitudFactory;
    private final SolicitudMapper solicitudMapper;
    private final FacultadMapper facultadMapper;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final CalendarioRepository calendarioRepository;
    private final CalendarioAcademicoMapper calendarioAcademicoMapper;



    public DecanaturaService(SolicitudRepository solicitudRepository,
                             AccionSolicitudFactory accionSolicitudFactory,
                             SolicitudMapper solicitudMapper,
                             FacultadMapper facultadMapper, EstudianteRepository estudianteRepository,
                             EstudianteMapper estudianteMapper,CalendarioRepository calendarioRepository,
                             CalendarioAcademicoMapper calendarioAcademicoMapper) {
        this.solicitudRepository = solicitudRepository;
        this.accionSolicitudFactory = accionSolicitudFactory;
        this.solicitudMapper = solicitudMapper;
        this.facultadMapper = facultadMapper;
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.calendarioRepository = calendarioRepository;
        this.calendarioAcademicoMapper = calendarioAcademicoMapper;

    }



    public SolicitudDTO cambiarEstado(String solicitudId, String accion) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new NotFoundException("Solicitud no encontrada"));

        AccionSolicitudCommand comando = accionSolicitudFactory.obtenerComando(accion);
        comando.accionSolicitud(solicitud);

        return solicitudMapper.toDTO(solicitudRepository.save(solicitud));
    }

    public List<SolicitudDTO> obtenerSolicitudesPorFacultad(FacultadDTO facultadDTO) {
        Facultad facultad = facultadMapper.toFacultad(facultadDTO);
        List<Solicitud> requestsList = solicitudRepository.findByFacultad(facultad);

        return requestsList.stream().map(solicitudMapper::toDTO).collect(Collectors.toList());
    }


    public List<SolicitudDTO> obtenerSolicitudesPorPrioridad(int prioridad) {
        List<Solicitud> s =  solicitudRepository.findByPrioridad(prioridad);
        return s.stream().map(solicitudMapper::toDTO).collect(Collectors.toList());
    }

    public List<SolicitudDTO> obtenerSolicitudesPendientes(FacultadDTO facultadDTO) {
        Facultad facultad = facultadMapper.toFacultad(facultadDTO);
        List<Solicitud> requestsList = solicitudRepository.findByFacultad(facultad);

        return requestsList.stream().
                filter(s->s.getEstado() == EstadoSolicitud.PENDIENTE).
                map(solicitudMapper::toDTO).collect(Collectors.toList());
    }
    public EstudianteDTO verInformacionEstudiante(String estudianteId) {
        var estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        return estudianteMapper.toDTO(estudiante);
    }


    /**
     * Calendario academico metodos
     */

    public CalendarioAcademicoDTO createAcademicCalendar(CalendarioAcademicoDTO calendarioAcademicoDTO) {
        CalendarioAcademico c = calendarioAcademicoMapper.toClass(calendarioAcademicoDTO);
        c.setActivo(true);
        desactivarCalendarios();
        return calendarioAcademicoMapper.toDTO(calendarioRepository.save(c));
    }

    @Transactional
    protected void desactivarCalendarios(){
        List<CalendarioAcademico> calendarios = calendarioRepository.findAll();
        calendarios.stream().filter(CalendarioAcademico::isActivo)
                .forEach(cale -> {cale.setActivo(false); calendarioRepository.save(cale);});
    }


    public CalendarioAcademicoDTO updateFinalDay(String finalDay, String id) {
        LocalDate f = LocalDate.parse(finalDay);
        CalendarioAcademico c = calendarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Calendario no encontrado"));
        c.setEnd(f);

        return  calendarioAcademicoMapper.toDTO(calendarioRepository.save(c));
    }

    public CalendarioAcademicoDTO updateStartDay(String startDay, String id) {
        LocalDate f = LocalDate.parse(startDay);
        CalendarioAcademico c = calendarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Calendario no encontrado"));
        c.setStart(f);
        return  calendarioAcademicoMapper.toDTO(calendarioRepository.save(c));
    }

}

package eci.edu.dows.profesorSuperO.service;


import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.FacultadMapper;
import eci.edu.dows.profesorSuperO.Util.SolicitudMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.repository.SolicitudRepository;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudCommand;
import eci.edu.dows.profesorSuperO.service.Acciones.AccionSolicitudFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecanaturaService {
    private final SolicitudRepository solicitudRepository;
    private final AccionSolicitudFactory accionSolicitudFactory;
    private final SolicitudMapper solicitudMapper;
    private final FacultadMapper facultadMapper;


    public DecanaturaService(SolicitudRepository solicitudRepository,
                             AccionSolicitudFactory accionSolicitudFactory,
                             SolicitudMapper solicitudMapper,
                             FacultadMapper facultadMapper) {
        this.solicitudRepository = solicitudRepository;
        this.accionSolicitudFactory = accionSolicitudFactory;
        this.solicitudMapper = solicitudMapper;
        this.facultadMapper = facultadMapper;
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
}

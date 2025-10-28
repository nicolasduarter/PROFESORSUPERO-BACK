package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioGrupo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitudCambioGrupoMapper {
    SolicitudCambioGrupoDTO toDTO(SolicitudCambioGrupo sDTO);
    SolicitudCambioGrupo toEntity(SolicitudCambioGrupoDTO sDto);
}

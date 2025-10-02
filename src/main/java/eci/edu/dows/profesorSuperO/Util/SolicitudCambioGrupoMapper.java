package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitudCambioGrupoMapper {
    SolicitudCambioGrupoDTO toDTO(SolicitudCambioGrupo sDTO);
    SolicitudCambioGrupo toEntity(SolicitudCambioGrupoDTO sDto);
}

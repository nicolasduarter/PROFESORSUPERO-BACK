package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioMateria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitudCambioMateriaMapper {
    SolicitudCambioMateriaDTO toDTO(SolicitudCambioMateria sDTO);
    SolicitudCambioMateria toEntity(SolicitudCambioMateriaDTO sDto);
}

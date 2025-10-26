package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioGrupo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MateriaMapper.class, GrupoMapper.class, EstudianteMapper.class})
public interface SolicitudCambioGrupoMapper {
    SolicitudCambioGrupoDTO toDTO(SolicitudCambioGrupo sDTO);
    SolicitudCambioGrupo toEntity(SolicitudCambioGrupoDTO sDto);
}

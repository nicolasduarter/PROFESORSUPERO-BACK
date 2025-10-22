package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioMateria;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {SolicitudCambioMateriaMapper.class, SolicitudCambioGrupoMapper.class})
public abstract class SolicitudMapper {

    @Autowired
    protected SolicitudCambioMateriaMapper cambioMateriaMapper;

    @Autowired
    protected SolicitudCambioGrupoMapper cambioGrupoMapper;

    public SolicitudDTO toDTO(Solicitud solicitud) {
        if (solicitud instanceof SolicitudCambioMateria) {
            return cambioMateriaMapper.toDTO((SolicitudCambioMateria) solicitud);
        } else if (solicitud instanceof SolicitudCambioGrupo) {
            return cambioGrupoMapper.toDTO((SolicitudCambioGrupo) solicitud);
        } else {
            throw new IllegalArgumentException(" solicitud desconocida");
        }
    }

    public Solicitud toEntity(SolicitudDTO dto) {
        if (dto instanceof SolicitudCambioMateriaDTO) {
            return cambioMateriaMapper.toEntity((SolicitudCambioMateriaDTO) dto);
        } else if (dto instanceof SolicitudCambioGrupoDTO) {
            return cambioGrupoMapper.toEntity((SolicitudCambioGrupoDTO) dto);
        } else {
            throw new IllegalArgumentException("DTO desconocido");
        }
    }
}

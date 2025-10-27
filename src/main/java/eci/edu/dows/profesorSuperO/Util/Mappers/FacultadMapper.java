package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.Facultad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MateriaMapper.class})
public interface FacultadMapper {
    Facultad toFacultad(FacultadDTO dto);
    FacultadDTO toDTO(Facultad f);
}

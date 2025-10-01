package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.Facultad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacultadMapper {
    Facultad toFacultad(FacultadDTO fdto);
    FacultadDTO toDTO(Facultad f);
}

package eci.edu.dows.profesorSuperO.Util;


import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClaseMapper {

    Clase toClass(ClaseDTO dto);
    ClaseDTO toDTO(Clase clase);
}

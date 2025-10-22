package eci.edu.dows.profesorSuperO.Util.Mappers;


import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClaseMapper {
    Clase toClass(ClaseDTO dto);
    ClaseDTO toDTO(Clase clase);
}

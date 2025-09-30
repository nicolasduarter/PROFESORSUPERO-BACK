package eci.edu.dows.profesorSuperO.Util;


import eci.edu.dows.profesorSuperO.model.Clase;
import eci.edu.dows.profesorSuperO.model.DTOS.ClaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClaseMapper {
    ClaseMapper INSTANCE = Mappers.getMapper(ClaseMapper.class);
    ClaseDTO toClass(Clase clase);
}

package eci.edu.dows.profesorSuperO.Util;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClaseMapper {
    ClaseMapper INSTANCE = Mappers.getMapper(ClaseMapper.class);

}

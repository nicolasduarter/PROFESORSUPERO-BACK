package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MateriaMapper {

    Materia toMateria(MateriaDTO dto);
    MateriaDTO toDto(Materia m);

}

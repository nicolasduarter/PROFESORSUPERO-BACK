package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Materia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MateriaMapper {

    Materia toMateria(MateriaDTO dto);
    MateriaDTO toDto(Materia m);

}

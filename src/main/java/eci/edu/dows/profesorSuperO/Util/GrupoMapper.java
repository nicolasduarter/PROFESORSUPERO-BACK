package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProfesorMapper.class, MateriaMapper.class, ClaseMapper.class, EstudianteMapper.class})
public interface GrupoMapper {

    GrupoDTO toDTO(Grupo grupo);
    Grupo toGrupo(GrupoDTO dto);
}

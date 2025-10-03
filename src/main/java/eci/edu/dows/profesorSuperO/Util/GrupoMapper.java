package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProfesorMapper.class, MateriaMapper.class, ClaseMapper.class})
public interface GrupoMapper {

    @Mapping(target = "estudiantesInscritos", expression = "java(grupo.getEstudiantes() != null ? grupo.getEstudiantes().size() : 0)")
    GrupoDTO toDTO(Grupo grupo);
    Grupo toGrupo(GrupoDTO dto);
}

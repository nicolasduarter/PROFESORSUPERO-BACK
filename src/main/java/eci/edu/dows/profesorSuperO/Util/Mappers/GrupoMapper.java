package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Grupo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProfesorMapper.class, MateriaMapper.class, ClaseMapper.class, EstudianteMapper.class})
public interface GrupoMapper {

    GrupoDTO toDTO(Grupo grupo);
    Grupo toGrupo(GrupoDTO dto);
}

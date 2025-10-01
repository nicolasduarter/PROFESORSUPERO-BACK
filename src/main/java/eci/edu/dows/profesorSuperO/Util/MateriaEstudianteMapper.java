package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.MateriaEstudianteDTO;
import eci.edu.dows.profesorSuperO.model.MateriaEstudiante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MateriaEstudianteMapper {
    MateriaEstudianteDTO toDTO(MateriaEstudiante materiaEstudiante);
    MateriaEstudiante toMateriaEstudiante(MateriaEstudianteDTO materiaEstudianteDTO);
}

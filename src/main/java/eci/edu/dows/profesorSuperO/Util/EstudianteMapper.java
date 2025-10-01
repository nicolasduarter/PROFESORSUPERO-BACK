package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface EstudianteMapper {
    Estudiante toEntity(EstudianteDTO dto);
    EstudianteDTO toDTO(Estudiante estudiante);

}

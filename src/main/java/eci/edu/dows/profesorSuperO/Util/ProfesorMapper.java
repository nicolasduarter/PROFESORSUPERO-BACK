package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Profesor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface ProfesorMapper {
    Profesor toEntity(ProfesorDTO dto);
    ProfesorDTO toDTO(Profesor p);
}

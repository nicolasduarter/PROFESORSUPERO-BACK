package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.UsuarioDTO;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Profesor;
import eci.edu.dows.profesorSuperO.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface UsuarioMapper {
    Estudiante toEntity(EstudianteDTO dto);
    EstudianteDTO toDTO(Estudiante estudiante);

}

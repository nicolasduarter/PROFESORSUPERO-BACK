package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.Usuarios.Decanatura;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.DecanaturaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface DecanaturaMapper {

    Decanatura toEntity(DecanaturaDTO dto);

    DecanaturaDTO toDTO(Decanatura decanatura);
}

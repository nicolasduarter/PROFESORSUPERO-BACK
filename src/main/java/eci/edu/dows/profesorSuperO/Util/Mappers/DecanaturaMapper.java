package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.Usuarios.Decanatura;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.DecanaturaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface DecanaturaMapper {

    @Mapping(target = "facultad", source = "facultad")
    Decanatura toEntity(DecanaturaDTO dto);

    @Mapping(target = "facultad", source = "facultad")
    DecanaturaDTO toDTO(Decanatura decanatura);
}

package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.Horario;
import eci.edu.dows.profesorSuperO.model.DTOS.HorarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GrupoMapper.class})
public interface HorarioMapper {

    Horario toHorario(HorarioDTO dto);
    HorarioDTO toDTO(Horario entity);
}


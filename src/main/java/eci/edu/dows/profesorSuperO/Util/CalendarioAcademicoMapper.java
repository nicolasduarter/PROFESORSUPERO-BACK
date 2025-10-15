package eci.edu.dows.profesorSuperO.Util;


import eci.edu.dows.profesorSuperO.model.CalendarioAcademico;
import eci.edu.dows.profesorSuperO.model.DTOS.CalendarioAcademicoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendarioAcademicoMapper {
    CalendarioAcademico toClass(CalendarioAcademicoDTO dto);
    CalendarioAcademicoDTO toDTO(CalendarioAcademico calendarioAcademico);
}

package eci.edu.dows.profesorSuperO.Util.Mappers;


import eci.edu.dows.profesorSuperO.model.DTOS.Request.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.Semaforo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SemaforoMapper {

    Semaforo toSemaforo(SemaforoDTO dto);
    SemaforoDTO toDTO(Semaforo semaforo);
}

package eci.edu.dows.profesorSuperO.Util;


import eci.edu.dows.profesorSuperO.model.DTOS.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.Semaforo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SemaforoMapper {

    Semaforo toClass(SemaforoDTO dto);
    SemaforoDTO toDTO(Semaforo semaforo);
}

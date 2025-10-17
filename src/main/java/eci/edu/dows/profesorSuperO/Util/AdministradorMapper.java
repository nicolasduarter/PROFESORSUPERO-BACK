package eci.edu.dows.profesorSuperO.Util;

import eci.edu.dows.profesorSuperO.model.Administrador;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.AdminDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {
    Administrador toAdmin(AdminDTO dto);
    AdminDTO toAdminDTO(Administrador admin);
}

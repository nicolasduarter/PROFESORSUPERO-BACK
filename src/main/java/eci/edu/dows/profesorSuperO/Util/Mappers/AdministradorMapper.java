package eci.edu.dows.profesorSuperO.Util.Mappers;

import eci.edu.dows.profesorSuperO.model.Usuarios.Administrador;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.AdminDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {
    Administrador toAdmin(AdminDTO dto);
    AdminDTO toAdminDTO(Administrador admin);
}

package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.AdminDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.newRolUserDTO;

public interface AdministradorService {
    AdminDTO newAdmin(AdminDTO dto);

    void deleteAdmin(String id);

    newRolUserDTO updateRolUser(String id, String permiso);
}

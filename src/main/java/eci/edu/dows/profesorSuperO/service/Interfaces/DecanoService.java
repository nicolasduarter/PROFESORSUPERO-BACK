package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.DecanaturaDTO;

public interface DecanoService {

    DecanaturaDTO createDean(DecanaturaDTO dto);

    DecanaturaDTO getDeanById(String id);

    DecanaturaDTO getDeanByEmail(String email);

    DecanaturaDTO getDeanByFullName(String nombre);

    DecanaturaDTO getDeanByFaculty(String facultyID);

    DecanaturaDTO updateDean(DecanaturaDTO dto);

    DecanaturaDTO updateDeanByMail(String id, String mail);

    DecanaturaDTO updateDeanByFaculty(String id, String idFaculty);

    DecanaturaDTO updateDeanByFullName(String id, String name);

    void deleteDean(String id);
}

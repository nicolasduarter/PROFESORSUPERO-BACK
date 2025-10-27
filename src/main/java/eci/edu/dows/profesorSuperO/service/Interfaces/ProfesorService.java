package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.ProfesorDTO;


public interface ProfesorService {

    ProfesorDTO createTeacher(ProfesorDTO dto);

    ProfesorDTO getTeacherById(String id);

    ProfesorDTO getTeacherByEmail(String email);

    ProfesorDTO getTeacherByFullName(String nombre);

    ProfesorDTO updateTeacher(ProfesorDTO dto);

    ProfesorDTO updateTeacherByFullName(String id, String name);

    ProfesorDTO updateTeacherByMail(String id, String mail);

    void deleteTeacher(String id);
}

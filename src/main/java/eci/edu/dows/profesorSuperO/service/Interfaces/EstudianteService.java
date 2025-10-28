package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.HorarioDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.EstudianteDTO;

import java.util.List;

public interface EstudianteService {

    EstudianteDTO createStudent(EstudianteDTO dto);

    EstudianteDTO getStudentById(String id);

    EstudianteDTO getStudentByEmail(String email);

    EstudianteDTO getStudentByFullName(String nombre);

    List<EstudianteDTO> getStudentByFaculty(String idFaculty);

    List<EstudianteDTO> getStudentBySemester(int semestre);

    EstudianteDTO updateStudent(EstudianteDTO dto);

    EstudianteDTO updateStudentByMail(String id, String mail);

    EstudianteDTO updateStudentBySemester(String id, int semestre);

    EstudianteDTO updateStudentByFaculty(String id, String idFaculty);

    EstudianteDTO updateStudentByFullName(String id, String name);

    HorarioDTO crearHorarioParaEstudiante(String idEstudiante, HorarioDTO horarioDTO);

    void deleteStudent(String id);
}

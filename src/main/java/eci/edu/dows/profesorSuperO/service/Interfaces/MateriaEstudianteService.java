package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.MateriaEstudiante;

import java.util.List;

public interface MateriaEstudianteService {

    MateriaEstudiante crearInscripcionMateria(Estudiante estudiante, Materia materia);

    MateriaEstudiante aprobarMateria(String idEstudiante, String idMateria, double nota);

    MateriaEstudiante cancelarMateria(String idEstudiante, String idMateria);

    MateriaEstudiante repobrarMateria(String idEstudiante, String idMateria, double nota);

    List<MateriaEstudiante> obtenerHistorial(String idEstudiante);
}

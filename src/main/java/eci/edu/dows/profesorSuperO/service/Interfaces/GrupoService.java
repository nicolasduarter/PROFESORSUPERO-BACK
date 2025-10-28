package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO2;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.ClaseDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;

import java.util.List;

public interface GrupoService {

    GrupoDTO crearGrupo(GrupoDTO dto);

    void eliminarGrupo(String id);

    GrupoDTO modificarCuposGrupo(String grupoId, int cupo);

    GrupoDTO agregarEstudianteAGrupo(String grupoId, String estudianteId);

    List<Estudiante> consultarListaEspera(String grupoId);

    GrupoDTO eliminarEstudianteAGrupo(String grupoId, String estudianteId);

    GrupoDTO eliminarDeListaEspera(String grupoId, String estudianteId);

    GrupoDTO agregarProfesorAGrupo(String grupoId, String profesorId);

    GrupoDTO eliminarProfesorAGrupo(String grupoId);

    GrupoDTO agregarClasesAGrupo(String grupoId, List<ClaseDTO> clases);

    GrupoDTO eliminarClasesAGrupo(String grupoId);

    GrupoDTO cambiarNombreAGrupo(String grupoId, String nombre);

    GrupoDTO updateMaximumCapacity(String grupoId, int cupo);

    GrupoDTO getMaximumCapacity(String grupoId);

    int getActualCapacity(String grupoId);

    GrupoDTO deleteStudentOfGroup(String grupoId, String estudianteId);

    GrupoDTO2 getMaximumCapacity2(String grupoId);

    GrupoDTO asignarMateriaYGrupo(String idEstudiante, String grupoId);
}
package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;

import java.util.List;

public interface MateriaService {

    MateriaDTO crearMateria(MateriaDTO materiaDTO);

    List<MateriaDTO> findByNombre(String nombre);

    void eliminarMateriaPorId(String id);

    MateriaDTO buscarPorId(String id);

    MateriaDTO actualizarCreditos(String id, int nuevosCreditos);

    MateriaDTO actualizarNombre(String id, String nombre);

    MateriaDTO agregarPrerequisito(String id, MateriaDTO materiaDTO);

    MateriaDTO eliminarPrerequisito(String id, MateriaDTO materiaDTO);

    MateriaDTO agregarPrerequisitos(String id, List<MateriaDTO> materiasDTO);

    MateriaDTO eliminarPrerequisitos(String id, List<MateriaDTO> materiasDTO);
}

package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.MateriaMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository, MateriaMapper materiaMapper) {
        this.materiaRepository = materiaRepository;
        this.materiaMapper = materiaMapper;
    }

    public MateriaDTO guardarMateria(MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toMateria(materiaDTO);
        return materiaMapper.toDto(materiaRepository.save(materia));
    }

    public List<MateriaDTO> findByNombre(String nombre) {
        return materiaRepository.findByNombre(nombre)
                .stream()
                .map(materiaMapper::toDto)
                .toList();
    }

    public void eliminarMateriaPorId(String id) {
        if (!materiaRepository.existsById(id)) {
            throw new NotFoundException("No existe la materia con ID " + id);
        }
        materiaRepository.deleteById(id);
    }

    public MateriaDTO buscarPorId(String id) {
        return materiaRepository.findById(id)
                .map(materiaMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
    }

    public MateriaDTO actualizarCreditos(String id, int nuevosCreditos) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.setCreditos(nuevosCreditos);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO actualizarNombre(String id, String nombre) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.setNombre(nombre);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO agregarPrerequisito(String id, MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.agregarPrerequisito(materiaMapper.toMateria(materiaDTO));
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO eliminarPrerequisito(String id, MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.eliminarPrerequisito(materiaMapper.toMateria(materiaDTO));
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO agregarPrerequisitos(String id, List<MateriaDTO> materiasDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        List<Materia> materias = materiasDTO.stream()
                .map(materiaMapper::toMateria)
                .toList();
        materia.agregarPrerequisitos(materias);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO eliminarPrerequisitos(String id, List<MateriaDTO> materiasDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        List<Materia> materias = materiasDTO.stream()
                .map(materiaMapper::toMateria)
                .toList();
        materia.eliminarPrerequisitos(materias);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }
}

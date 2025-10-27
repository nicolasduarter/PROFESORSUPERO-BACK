package eci.edu.dows.profesorSuperO.service.Implementaciones;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.MateriaMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    @Autowired
    public MateriaServiceImpl(MateriaRepository materiaRepository, MateriaMapper materiaMapper) {
        this.materiaRepository = materiaRepository;
        this.materiaMapper = materiaMapper;
    }

    public MateriaDTO crearMateria(MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toMateria(materiaDTO);
        Materia materiaGuardada = materiaRepository.save(materia);
        return materiaMapper.toDto(materiaGuardada);
    }

    public List<MateriaDTO> findByNombre(String nombre) {
        return materiaRepository.findByNombre(nombre)
                .stream()
                .map(materiaMapper::toDto)
                .toList();
    }

    public void eliminarMateriaPorId(String id) {
        if (!materiaRepository.existsById(id)) {
            throw new NotFoundException("no se encontro materia con esa ID");
        }
        materiaRepository.deleteById(id);
    }

    public MateriaDTO buscarPorId(String id) {
        return materiaRepository.findById(id)
                .map(materiaMapper::toDto)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
    }

    public MateriaDTO actualizarCreditos(String id, int nuevosCreditos) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
        materia.setCreditos(nuevosCreditos);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO actualizarNombre(String id, String nombre) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
        materia.setNombre(nombre);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO agregarPrerequisito(String id, MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
        materia.agregarPrerequisito(materiaMapper.toMateria(materiaDTO));
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO eliminarPrerequisito(String id, MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
        materia.eliminarPrerequisito(materiaMapper.toMateria(materiaDTO));
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO agregarPrerequisitos(String id, List<MateriaDTO> materiasDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
        List<Materia> materias = materiasDTO.stream()
                .map(materiaMapper::toMateria)
                .toList();
        materia.agregarPrerequisitos(materias);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }

    public MateriaDTO eliminarPrerequisitos(String id, List<MateriaDTO> materiasDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro materia con esa ID"));
        List<Materia> materias = materiasDTO.stream()
                .map(materiaMapper::toMateria)
                .toList();
        materia.eliminarPrerequisitos(materias);
        Materia actualizada = materiaRepository.save(materia);
        return materiaMapper.toDto(actualizada);
    }
}

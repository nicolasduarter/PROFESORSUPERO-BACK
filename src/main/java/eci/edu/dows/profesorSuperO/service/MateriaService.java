package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.MateriaMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {
    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository,MateriaMapper materiaMapper) {
        this.materiaRepository = materiaRepository;
        this.materiaMapper = materiaMapper;
    }


    public MateriaDTO guardarMateria(MateriaDTO materiaDTO){
        Materia materia = materiaMapper.toMateria(materiaDTO);
        return materiaMapper.toDto(materiaRepository.save(materia));
    }

    public List<MateriaDTO> findByNombre(String nombre){
        return materiaRepository.findByNombre(nombre).stream().map(materiaMapper::toDto).toList();
    }

    public void eliminarMateriaPorId(String id) {
        if (!materiaRepository.existsById(id)) {
            throw new NotFoundException("No exite la  materia con ID " + id );
        }
        materiaRepository.deleteById(id);
    }



    public Optional<MateriaDTO> buscarPorId(String id) {
         return materiaRepository.findById(id)
                .map(materiaMapper::toDto);
    }


    public void actualizarCreditos(String id, int nuevosCreditos) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.setCreditos(nuevosCreditos);
        materiaRepository.save(materia);
    }

    public void actualizarNombre(String id,String nombre) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.setNombre(nombre);
        materiaRepository.save(materia);
    }

    public void agregarPrerequisito(String id,MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.agregarPrerequisito(materiaMapper.toMateria(materiaDTO));
        materiaRepository.save(materia);
    }
    public void eliminarPrerequisito(String id, MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        materia.eliminarPrerequisito(materiaMapper.toMateria(materiaDTO));
        materiaRepository.save(materia);
    }


    public void agregarPrerequisitos(String id,List<MateriaDTO> materiasDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        List<Materia> materias =  materiasDTO.stream().map(materiaMapper::toMateria).toList();
        materia.agregarPrerequisitos(materias);
        materiaRepository.save(materia);
    }

    public void eliminarPrerequisitos(String id, List<MateriaDTO> materiasDTO) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Materia no encontrada con id: " + id));
        List<Materia> materias =  materiasDTO.stream().map(materiaMapper::toMateria).toList();
        materia.eliminarPrerequisitos(materias);
        materiaRepository.save(materia);
    }


}

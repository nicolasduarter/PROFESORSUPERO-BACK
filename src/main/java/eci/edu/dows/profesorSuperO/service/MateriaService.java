package eci.edu.dows.profesorSuperO.service;

import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }


    public Materia guardarMateria(Materia materia){
        return   materiaRepository.save(materia);
    }


    public void eliminarMateria(Materia materia){
        materiaRepository.delete(materia);
    }

    public List<Materia> findByNombre(String nombre){
       return  materiaRepository.findByNombre(nombre);
    }

    public void eliminarMateriaPorId(String id) {
        if (!materiaRepository.existsById(id)) {
            throw new RuntimeException("No exite la  materia con ID " + id );
        }
        materiaRepository.deleteById(id);
    }

    public List<Materia> buscarPorEstado(String estado) {
        return materiaRepository.findByEstado(estado);
    }

    public Optional<Materia> buscarPorId(String id) {
        return materiaRepository.findById(id);
    }

    public void actualizarCreditos(String id, int nuevosCreditos) {
        Optional<Materia> materiaOpt = buscarPorId(id);
        if (materiaOpt.isEmpty()) {
            throw new RuntimeException("Materia no encontrada con id: " + id);
        }
        Materia materia = materiaOpt.get();
        materia.setCreditos(nuevosCreditos);
        guardarMateria(materia);
    }

}

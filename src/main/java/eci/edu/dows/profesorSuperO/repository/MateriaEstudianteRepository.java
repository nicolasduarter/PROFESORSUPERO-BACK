package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.MateriaEstudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaEstudianteRepository extends MongoRepository<MateriaEstudiante, String> {
    List<MateriaEstudiante> findByEstudiante_Id(String idEstudiante);
    List<MateriaEstudiante> findByMateria_Id(String idMateria);
    Optional<MateriaEstudiante> findByEstudiante_IdAndMateria_Id(String idEstudiante, String idMateria);
}

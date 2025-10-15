package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Clase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface ClaseRepository extends MongoRepository<Clase, String> {
    List<Clase> findBySalon(String salon);
    List<Clase> findByHoraInicioAndHoraFin(LocalTime horaInicio, LocalTime horaFin);
    List<Clase> findByidClase(String nombre);
    List<Clase> findByidClase(String nombre, String estado);
    List<Clase> findByDiaSemana(DayOfWeek diaSemana);
}

package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.model.CalendarioAcademico;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudDTO;

import eci.edu.dows.profesorSuperO.repository.CalendarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class ValidacionSolicitudValida implements ConstraintValidator<SolicitudValida, SolicitudDTO> {

    @Autowired
    private  CalendarioRepository calendarioRepository;

    public ValidacionSolicitudValida(CalendarioRepository calendarioRepository) {
        this.calendarioRepository = calendarioRepository;
    }


    private boolean horarioAdecuado(SolicitudDTO solicitud){
        CalendarioAcademico c = calendarioRepository.findTopByOrderByStartDesc().orElseThrow(() -> new NotFoundException("Calendario no encontrado"));

        LocalDate start =  c.getStart();
        LocalDate end =  c.getEnd();

        LocalDate sDate = solicitud.getFecha();

        return (sDate.isEqual(start) || sDate.isAfter(start)) &&
                (sDate.isEqual(end) || sDate.isBefore(end));
    }

    @Override
    public boolean isValid(SolicitudDTO solicitudDTO, ConstraintValidatorContext constraintValidatorContext) {
        return horarioAdecuado(solicitudDTO);
    }
}

package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.Solicitud;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidacionSolicitudValida implements ConstraintValidator<SolicitudValida, Solicitud> {


    private boolean horarioAdecuado(Solicitud solicitud){
        LocalDate fechaInicio = solicitud.getCalendarioAcademico().getStart();
        LocalDate fechaFinal = solicitud.getCalendarioAcademico().getEnd();
        LocalDate fechaSoli = solicitud.getFecha();



        System.out.println("fechaSoli: "+fechaSoli.toString());
        System.out.println("fechaInicio: "+fechaInicio.toString());
        System.out.println("fechaFinal: "+fechaFinal.toString());

        boolean dentroFechas= (fechaSoli.isEqual(fechaInicio) || fechaSoli.isAfter(fechaInicio)) &&
                (fechaSoli.isEqual(fechaFinal) || fechaSoli.isBefore(fechaFinal));


        return dentroFechas ;
    }





    @Override
    public boolean isValid(Solicitud solicitud, ConstraintValidatorContext constraintValidatorContext) {
        return horarioAdecuado(solicitud);
    }
}

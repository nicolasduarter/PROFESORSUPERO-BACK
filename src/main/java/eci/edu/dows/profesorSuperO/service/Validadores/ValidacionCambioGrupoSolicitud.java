package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidacionCambioGrupoSolicitud implements ConstraintValidator<SolicitudValidaCambioGrupo, SolicitudCambioGrupo> {

    private boolean mismoGrupo(SolicitudCambioGrupo solicitud) {
        return solicitud.getGrupo().getIdGrupo().equals(solicitud.getGrupoCambio().getIdGrupo());
    }

    private boolean mismaMateria(SolicitudCambioGrupo solicitud){
        Materia m = solicitud.getMateriaProblema();

        return m.getGrupos().contains(solicitud.getGrupoCambio());
    }

    private boolean horarioAdecuado(SolicitudCambioGrupo solicitud){
        LocalDate fechaSoli = solicitud.getFecha();

        LocalDate fechaInicio =  solicitud.getCalendarioAcademico().getEnd();
        LocalDate fechaFinal = solicitud.getCalendarioAcademico().getStart();

        boolean noMasGrande = !fechaSoli.isAfter(fechaFinal);
        boolean noMasPeque = fechaSoli.isBefore(fechaInicio);

        return noMasGrande && noMasPeque;
    }

    private boolean grupoLLeno(SolicitudCambioGrupo solicitud){
        Grupo grupo = solicitud.getGrupoCambio();
        int cupos = grupo.getCupo();
        int cuosMax = grupo.getCuposMax();
        if(cupos< cuosMax){
            return true;
        }else{
            return false;
        }

    }





    @Override
    public boolean isValid(SolicitudCambioGrupo solicitud, ConstraintValidatorContext constraintValidatorContext) {
        if (solicitud == null) return false;


        return false;
    }
}

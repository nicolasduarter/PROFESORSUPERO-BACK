package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidacionCambioGrupoSolicitud implements ConstraintValidator<SolicitudValidaCambioGrupo, SolicitudCambioGrupo> {



    private boolean mismaMateria2(SolicitudCambioGrupo solicitud){
        Materia m = solicitud.getMateriaProblema();
        return m.getGrupos().contains(solicitud.getGrupo());
    }

    private boolean mismaMateria(SolicitudCambioGrupo solicitud){
        Materia m = solicitud.getMateriaProblema();

        return m.getGrupos().contains(solicitud.getGrupoCambio());
    }



    private boolean grupoLLeno(SolicitudCambioGrupo solicitud){
        Grupo grupo = solicitud.getGrupoCambio();
        int cupos = grupo.getCupo();
        int cuosMax = grupo.getCuposMax();

        return cupos < cuosMax;
    }




    @Override
    public boolean isValid(SolicitudCambioGrupo solicitud, ConstraintValidatorContext context) {
        if (solicitud == null) return false;

        return grupoLLeno(solicitud)
                && mismaMateria(solicitud)
                && mismaMateria2(solicitud);
    }
}

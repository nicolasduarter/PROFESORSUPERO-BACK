package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacionCambioGrupoSolicitud implements ConstraintValidator<SolicitudValidaCambioGrupo, SolicitudCambioGrupo> {

    private boolean mismoGrupo(SolicitudCambioGrupo solicitud) {
        return solicitud.getGrupo().getIdGrupo().equals(solicitud.getGrupoCambio().getIdGrupo());
    }

    private boolean mismaMateria(SolicitudCambioGrupo solicitud){
        Materia m = solicitud.getMateriaProblema();

        return m.getGrupos().contains(solicitud.getGrupoCambio());
    }




    @Override
    public boolean isValid(SolicitudCambioGrupo solicitud, ConstraintValidatorContext constraintValidatorContext) {
        if (solicitud == null) return false;


        return false;
    }
}

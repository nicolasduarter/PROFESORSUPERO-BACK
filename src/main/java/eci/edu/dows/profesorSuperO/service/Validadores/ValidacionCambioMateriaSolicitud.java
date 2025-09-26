package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioMateria;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacionCambioMateriaSolicitud implements ConstraintValidator<SolicitudValidaCambioMateria, SolicitudCambioMateria> {


    private boolean mismaMateria(SolicitudCambioMateria solicitud){
        Materia m = solicitud.getMateriaCambio();
        return m.getGrupos().contains(solicitud.getGrupoCambio());
    }


    private boolean materiaPertenceAFacultad(SolicitudCambioMateria solicitud){
        Materia m = solicitud.getMateriaCambio();
        return solicitud.getFacultadOBJ().getMaterias().contains(m);
    }





    @Override
    public boolean isValid(SolicitudCambioMateria solicitudCambioMateria, ConstraintValidatorContext constraintValidatorContext) {
        return mismaMateria(solicitudCambioMateria)
                && materiaPertenceAFacultad(solicitudCambioMateria);
    }
}

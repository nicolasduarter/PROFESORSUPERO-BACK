package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidacionCambioGrupoSolicitud implements ConstraintValidator<SolicitudValidaCambioGrupo, SolicitudCambioGrupo> {



    private boolean mismaMateria(SolicitudCambioGrupo solicitud){
        Materia m = solicitud.getMateriaProblema();
        if (m == null || m.getGrupos() == null) return false;

        return m.getGrupos().stream()
                .anyMatch(g -> g.getIdGrupo().equals(solicitud.getGrupoCambio().getIdGrupo()));
    }

    private boolean mismaMateria2(SolicitudCambioGrupo solicitud){
        Materia m = solicitud.getMateriaProblema();
        if (m == null || m.getGrupos() == null) return false;

        return m.getGrupos().stream()
                .anyMatch(g -> g.getIdGrupo().equals(solicitud.getGrupo().getIdGrupo()));
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

        if(!grupoLLeno(solicitud)){System.out.println("Grupo Lleno");}
        if(!mismaMateria(solicitud)){System.out.println("Materia Lleno");}
        if(!mismaMateria2(solicitud)){System.out.println("Materia Lleno");}


        return mismaMateria(solicitud) &&
                mismaMateria2(solicitud) &&
                grupoLLeno(solicitud);

    }
}

package eci.edu.dows.profesorSuperO.service.Validadores;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidacionCambioGrupoSolicitud implements ConstraintValidator<SolicitudValidaCambioGrupo, SolicitudCambioGrupoDTO> {



    private boolean mismaMateriayGrupo(SolicitudCambioGrupoDTO solicitud){
        MateriaDTO m = solicitud.getMateriaProblema();
        if (m == null ) return false;
        return  m.getId().equals(solicitud.getGrupo().getMateria().getId());

    }

    private boolean mismaMateria2(SolicitudCambioGrupoDTO solicitud){

        MateriaDTO m = solicitud.getMateriaProblema();
        if (m == null ) return false;


        return  m.getId().equals(solicitud.getGrupoCambio().getMateria().getId());

    }



    private boolean grupoTieneCupo(SolicitudCambioGrupoDTO solicitud){
        return  solicitud.getGrupoCambio().getCupo() < solicitud.getGrupoCambio().getCuposMax();
    }


    @Override
    public boolean isValid(SolicitudCambioGrupoDTO solicitudDTO, ConstraintValidatorContext context) {
        if (solicitudDTO == null) return false;

        return grupoTieneCupo(solicitudDTO) && mismaMateria2(solicitudDTO) && mismaMateriayGrupo(solicitudDTO);

    }
}

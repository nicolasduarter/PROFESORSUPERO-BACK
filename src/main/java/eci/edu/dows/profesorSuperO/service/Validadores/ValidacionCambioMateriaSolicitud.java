//package eci.edu.dows.profesorSuperO.service.Validadores;
//
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;
//import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioMateriaDTO;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class ValidacionCambioMateriaSolicitud implements ConstraintValidator<SolicitudValidaCambioMateria, SolicitudCambioMateriaDTO> {
//
//
//    private boolean mismaMateria(SolicitudCambioMateriaDTO solicitud){
//        MateriaDTO m = solicitud.getMateriaCambio();
//        if (m == null ) return false;
//        return  m.getId().equals(solicitud.getGrupoCambio().getMateria().getId());
//    }
//
//
//    private boolean materiaPertenceAFacultad(SolicitudCambioMateriaDTO solicitud){
//        String materiaId = solicitud.getMateriaCambio().getId();
//        return solicitud.getEstudiante()
//                .getFacultad()
//                .getMaterias()
//                .stream()
//                .anyMatch(m -> materiaId.equals(m.getId()));
//    }
//
//
//
//
//
//    @Override
//    public boolean isValid(SolicitudCambioMateriaDTO solicitudCambioMateria, ConstraintValidatorContext constraintValidatorContext) {
//        return materiaPertenceAFacultad(solicitudCambioMateria) && mismaMateria(solicitudCambioMateria);
//    }
//}

package eci.edu.dows.profesorSuperO.service.Validadores;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidacionCambioMateriaSolicitud.class)
public @interface SolicitudValidaCambioMateria {
    String message() default "Error en datos de la soli -> materia cambio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

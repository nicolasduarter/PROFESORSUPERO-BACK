package eci.edu.dows.profesorSuperO.service.Validadores;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SolicitudCambioGrupo.class)
public  @interface SolicitudValidaCambioGrupo {
    String message() default "Error en datos de la soli";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

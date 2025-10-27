package eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter

@Document("Profesores")
public class ProfesorDTO extends UsuarioDTO {


}

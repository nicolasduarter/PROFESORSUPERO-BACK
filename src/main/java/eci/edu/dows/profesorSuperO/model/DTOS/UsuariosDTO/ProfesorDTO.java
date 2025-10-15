package eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter

@Document("Profesores")
public class ProfesorDTO extends UsuarioDTO {


}

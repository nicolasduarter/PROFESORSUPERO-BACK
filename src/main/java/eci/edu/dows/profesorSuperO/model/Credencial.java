package eci.edu.dows.profesorSuperO.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("credenciales")
public class Credencial {
    @Id
    private String id;

    private String usuario;
    private String constra;
    private String usuarioId;
}

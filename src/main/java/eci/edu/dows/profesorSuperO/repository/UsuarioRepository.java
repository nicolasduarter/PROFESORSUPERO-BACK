package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findByCorreo(String correo);
    List<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findByPermiso(String permiso);
}
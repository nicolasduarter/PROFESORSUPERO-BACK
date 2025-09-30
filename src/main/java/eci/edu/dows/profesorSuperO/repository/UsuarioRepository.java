package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findByPermiso(Permisos permiso);
}
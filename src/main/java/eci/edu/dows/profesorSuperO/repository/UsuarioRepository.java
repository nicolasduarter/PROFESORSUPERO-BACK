package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByFullName(String fullName);
    Optional<Usuario> findByPermiso(Permisos permiso);

    long countByPermiso(Permisos permiso);

}
package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;


import eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioRegistroMapper {

    UsuarioRegistroMapper INSTANCE = Mappers.getMapper(UsuarioRegistroMapper.class);

    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "permiso", target = "permiso")
    @Mapping(source = "facultad", target = "facultad")
    @Mapping(source = "semestre", target = "semestre")
    EstudianteDTO usuarioRegistroDTOToEstudianteDTO(UsuarioRegistroDTO usuarioRegistroDTO);
}
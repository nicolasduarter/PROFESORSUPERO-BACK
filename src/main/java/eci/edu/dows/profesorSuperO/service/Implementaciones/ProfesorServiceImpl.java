package eci.edu.dows.profesorSuperO.service.Implementaciones;


import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.ProfesorMapper;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;

import eci.edu.dows.profesorSuperO.model.Profesor;
import eci.edu.dows.profesorSuperO.model.Usuario;
import eci.edu.dows.profesorSuperO.repository.ProfesorRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper, UsuarioRepository usuarioRepository) {
            this.profesorRepository = profesorRepository;
            this.profesorMapper = profesorMapper;
            this.usuarioRepository = usuarioRepository;
    }

    /**
     *Create
     */

    public ProfesorDTO createTeacher(ProfesorDTO dto) {
        Profesor p = profesorMapper.toProfesor(dto);
        return profesorMapper.toDTO(profesorRepository.save(p));
    }

    /**
     *Read
     */

    //by id
    public ProfesorDTO getTeacherById(String id){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isProfesor(u);
    }


    //By mail
    public ProfesorDTO getTeacherByEmail(String email){
        Usuario u = usuarioRepository.findByCorreo(email).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isProfesor(u);
    }

    //By full name
    public ProfesorDTO getTeacherByFullName(String nombre){
        Usuario u = usuarioRepository.findByFullName(nombre).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isProfesor(u);
    }

    /**
     *Update
     */

    //Completamente
    public ProfesorDTO updateTeacher(ProfesorDTO dto){
        Usuario u = usuarioRepository.findById(dto.getId()).orElseThrow(()->new NotFoundException("Usuario no encontrado"));

        if(isProfesorBoolean(u)){
            Profesor  teacher = (Profesor) u;
            teacher.setCorreo(dto.getCorreo());
            teacher.setFullName(dto.getFullName());
            try {
                teacher.setPermiso(Permisos.valueOf(dto.getPermiso().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new NotFoundException("Permiso no encontrado");
            }

            return profesorMapper.toDTO(profesorRepository.save(teacher));
        }

        throw new NotFoundException("Profesor invalido ");
    }



    //By full name
    public ProfesorDTO updateTeacherByFullName(String id, String name){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isProfesorBoolean(u)){
            Profesor teacher = (Profesor) u;
            teacher.setFullName(name);
            return profesorMapper.toDTO(profesorRepository.save(teacher));
        }
        throw new NotFoundException("Estudiante invalido ");
    }

    //Update mail
    public ProfesorDTO updateTeacherByMail(String id, String mail){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isProfesorBoolean(u)){
            Profesor teacher = (Profesor) u;
            teacher.setCorreo(mail);
            return profesorMapper.toDTO(profesorRepository.save(teacher));
        }
        throw new NotFoundException("Estudiante invalido ");
    }


    /**
     * Delete
     */

    public void deleteTeacher(String id) {
        usuarioRepository.deleteById(id);
    }

    private ProfesorDTO isProfesor(Usuario u){
        if (u instanceof Profesor) {
            return profesorMapper.toDTO((Profesor) u);
        } else {
            throw new NotFoundException("No es un profesor");
        }
    }


    private boolean isProfesorBoolean(Usuario u){
        return u instanceof  Profesor;
    }
}

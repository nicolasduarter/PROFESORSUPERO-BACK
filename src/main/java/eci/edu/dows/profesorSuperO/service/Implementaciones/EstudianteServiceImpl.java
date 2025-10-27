package eci.edu.dows.profesorSuperO.service.Implementaciones;


import eci.edu.dows.profesorSuperO.Util.Mappers.EstudianteMapper;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import eci.edu.dows.profesorSuperO.repository.EstudianteRepository;
import eci.edu.dows.profesorSuperO.repository.FacultadRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final FacultadRepository facultadRepository;

    @Autowired
    public EstudianteServiceImpl(UsuarioRepository usuarioRepository,
                                 EstudianteRepository estudianteRepository,
                                 EstudianteMapper estudianteMapper, FacultadRepository facultadRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.facultadRepository = facultadRepository;
    }

    /**
     *Create
    */

    public EstudianteDTO createStudent(EstudianteDTO dto) {
        Estudiante estudiante = estudianteMapper.toEstudiante(dto);

        return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
    }

    /**
     *Read
     */

    //By id
    public  EstudianteDTO getStudentById(String id){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isEstudiante(u);
    }


    //By mail
    public EstudianteDTO getStudentByEmail(String email){
        Usuario u = usuarioRepository.findByCorreo(email).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isEstudiante(u);
    }

    //By full name

    public EstudianteDTO getStudentByFullName(String nombre){
        Usuario u = usuarioRepository.findByFullName(nombre).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isEstudiante(u);
    }

    //By Faculty
    public List<EstudianteDTO> getStudentByFaculty(String idFaculty){
        Facultad f = facultadRepository.findById(idFaculty)
                .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));

        List<Estudiante> listaCompleta = estudianteRepository.findByFacultadId(f.getId());


        return listaCompleta.stream().map(estudianteMapper::toDTO).collect(Collectors.toList());
    }



    //By semester
    public List<EstudianteDTO> getStudentBySemester(int semestre){
        List<Estudiante> listaCompleta = estudianteRepository.findBySemestre(semestre);
        return listaCompleta.stream().map(estudianteMapper::toDTO).collect(Collectors.toList());
    }


    /**
     *Update
     */

    //Completamente
    public EstudianteDTO updateStudent(EstudianteDTO dto){
        Usuario u = usuarioRepository.findById(dto.getId()).orElseThrow(()->new NotFoundException("Usuario no encontrado"));

        if(isEstudianteBolean(u)){
            Estudiante estudiante = (Estudiante) u;
            estudiante.setCorreo(dto.getCorreo());
            estudiante.setFullName(dto.getFullName());
            try {
                estudiante.setPermiso(Permisos.valueOf(dto.getPermiso().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new NotFoundException("Permiso no encontrado");
            }

            Facultad f = facultadRepository.findById(dto.getFacultad().getId())
                    .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));
            estudiante.setFacultad(f);
            estudiante.setSemestre(dto.getSemestre());
            return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
        }

        throw new NotFoundException("Estudiante invalido ");

    }

    //Update mail
    public EstudianteDTO updateStudentByMail(String id, String mail){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isEstudianteBolean(u)){
            Estudiante estudiante = (Estudiante) u;
            estudiante.setCorreo(mail);
            return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
        }
        throw new NotFoundException("Estudiante invalido ");
    }

    //By semester
    public EstudianteDTO updateStudentBySemester(String id, int semestre){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isEstudianteBolean(u)){
            Estudiante estudiante = (Estudiante) u;
            estudiante.setSemestre(semestre);
            return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
        }
        throw new NotFoundException("Estudiante invalido ");
    }


    //By Faculty
    public EstudianteDTO updateStudentByFaculty(String id, String idFaculty){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isEstudianteBolean(u)){
            Estudiante estudiante = (Estudiante) u;
            Facultad f = facultadRepository.findById(idFaculty)
                    .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));
            estudiante.setFacultad(f);
            return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
        }
        throw new NotFoundException("Estudiante invalido ");
    }



    //By full name
    public EstudianteDTO updateStudentByFullName(String id, String name){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isEstudianteBolean(u)){
            Estudiante estudiante = (Estudiante) u;
            estudiante.setFullName(name);
            return estudianteMapper.toDTO(estudianteRepository.save(estudiante));
        }
        throw new NotFoundException("Estudiante invalido ");
    }


    /**
     * Delete
     */

    public void deleteStudent(String id) {
        usuarioRepository.deleteById(id);
    }


    private EstudianteDTO isEstudiante(Usuario u){
        if (u instanceof Estudiante) {
            return estudianteMapper.toDTO((Estudiante) u);
        } else {
            throw new NotFoundException("No es un estudiante");
        }
    }

    private boolean isEstudianteBolean(Usuario u){
        return u instanceof Estudiante;
    }



}

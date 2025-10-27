package eci.edu.dows.profesorSuperO.service.Implementaciones;


import eci.edu.dows.profesorSuperO.Util.Mappers.DecanaturaMapper;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.DecanaturaDTO;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Usuarios.Decanatura;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import eci.edu.dows.profesorSuperO.repository.DecanaturaRepository;
import eci.edu.dows.profesorSuperO.repository.FacultadRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.DecanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecanoServiceImpl implements DecanoService {
    private final DecanaturaMapper decanaturaMapper;
    private final UsuarioRepository usuarioRepository;
    private final DecanaturaRepository decanaturaRepository;
    private final FacultadRepository facultadRepository;

    @Autowired
    public DecanoServiceImpl(DecanaturaMapper decanaturaMapper, UsuarioRepository usuarioRepository,
                             DecanaturaRepository decanaturaRepository, FacultadRepository facultadRepository) {
        this.decanaturaMapper = decanaturaMapper;
        this.usuarioRepository = usuarioRepository;
        this.decanaturaRepository = decanaturaRepository;
        this.facultadRepository = facultadRepository;
    }

    /**
     *Create
     */

    public DecanaturaDTO createDean(DecanaturaDTO dto) {
        Decanatura d = decanaturaMapper.toEntity(dto);
        return decanaturaMapper.toDTO(decanaturaRepository.save(d));
    }


    /**
     *Read
     */

    //by id
    public DecanaturaDTO getDeanById(String id){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        System.out.println("Usuario encontrado: " + u.getClass().getName());
        return isDean(u);
    }


    //By mail
    public DecanaturaDTO getDeanByEmail(String email){
        Usuario u = usuarioRepository.findByCorreo(email).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isDean(u);
    }

    //By full name
    public DecanaturaDTO getDeanByFullName(String nombre){
        Usuario u = usuarioRepository.findByFullName(nombre).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return isDean(u);
    }


    //By Faculty
    public DecanaturaDTO getDeanByFaculty(String facultyID){
        Facultad f = facultadRepository.findById(facultyID)
                .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));

        Decanatura d = decanaturaRepository.findByFacultad(f).orElseThrow(() -> new NotFoundException("Decanatura no encontrada"));
        return  decanaturaMapper.toDTO(d);
    }



    /**
     *Update
     */

    //Completamente
    public DecanaturaDTO updateDean(DecanaturaDTO dto){
        Usuario u = usuarioRepository.findById(dto.getId()).orElseThrow(()->new NotFoundException("Usuario no encontrado"));

        if(isDeanBoolean(u)){
            Decanatura decano = (Decanatura) u;
            decano.setCorreo(dto.getCorreo());
            decano.setFullName(dto.getFullName());
            try {
                decano.setPermiso(Permisos.valueOf(dto.getPermiso().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new NotFoundException("Permiso no encontrado");
            }

            Facultad f = facultadRepository.findById(dto.getFacultad().getId())
                    .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));
            decano.setFacultad(f);
            return decanaturaMapper.toDTO(decanaturaRepository.save(decano));
        }

        throw new NotFoundException("Decano/a invalido ");

    }

    //Update mail
    public DecanaturaDTO updateDeanByMail(String id, String mail){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isDeanBoolean(u)){
            Decanatura decano = (Decanatura) u;
            decano.setCorreo(mail);
            return decanaturaMapper.toDTO(decanaturaRepository.save(decano));
        }
        throw new NotFoundException("Decano/a invalido ");
    }

    //By Faculty
    public DecanaturaDTO updateDeanByFaculty(String id, String idFaculty){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isDeanBoolean(u)){
            Decanatura decano = (Decanatura) u;
            Facultad f = facultadRepository.findById(idFaculty)
                    .orElseThrow(() -> new NotFoundException("Facultad no encontrada"));
            decano.setFacultad(f);
            return decanaturaMapper.toDTO(decanaturaRepository.save(decano));
        }
        throw new NotFoundException("Decano/a invalido ");
    }



    //By full name
    public DecanaturaDTO updateDeanByFullName(String id, String name){
        Usuario u = usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        if(isDeanBoolean(u)){
            Decanatura decano = (Decanatura) u;
            decano.setFullName(name);
            return decanaturaMapper.toDTO(decanaturaRepository.save(decano));
        }
        throw new NotFoundException("Estudiante invalido ");
    }

    /**
     * Delete
     */

    public void deleteDean(String id) {
        usuarioRepository.deleteById(id);
    }


    private DecanaturaDTO isDean(Usuario u){
        if (u instanceof Decanatura) {
            return decanaturaMapper.toDTO((Decanatura) u);
        } else {
            throw new NotFoundException("No es un Decano");
        }
    }


    private boolean isDeanBoolean(Usuario u){
        return u instanceof  Decanatura;
    }
}

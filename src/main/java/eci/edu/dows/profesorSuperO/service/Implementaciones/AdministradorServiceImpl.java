package eci.edu.dows.profesorSuperO.service.Implementaciones;


import eci.edu.dows.profesorSuperO.Util.Mappers.AdministradorMapper;
import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Usuarios.Administrador;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.AdminDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.newRolUserDTO;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import eci.edu.dows.profesorSuperO.repository.FacultadRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import eci.edu.dows.profesorSuperO.service.Interfaces.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorMapper adminMapper;
    private final UsuarioRepository userRepository;
    private final FacultadRepository facultadRepository;

    @Autowired
    public AdministradorServiceImpl(AdministradorMapper mapper, UsuarioRepository userRepository, FacultadRepository facultadRepository) {
        this.adminMapper = mapper;
        this.userRepository = userRepository;
        this.facultadRepository = facultadRepository;
    }

    public AdminDTO newAdmin(AdminDTO dto){
        Administrador admin = adminMapper.toAdmin(dto);
        return adminMapper.toAdminDTO(userRepository.save(admin));
    }

    public void deleteAdmin(String id){
        Usuario u = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        if (u.getPermiso() == Permisos.ADMINISTRATOR) {
            long totalAdmins = userRepository.countByPermiso(Permisos.ADMINISTRATOR);

            if (totalAdmins <= 1) {
                throw new IllegalStateException("Debe haber al menos un admin  en el sistema.");
            }
        }
        userRepository.deleteById(id);

    }


    public newRolUserDTO updateRolUser(String id, String permiso){
        Usuario u =  userRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        Permisos p = esPermiso(permiso);

        newRolUserDTO dto = new newRolUserDTO();

        return  dto;

    }



    public void crearFacultadSinMaterias(String id, String nombre) {
        Facultad facultad = new Facultad();
        facultad.setId(id);
        try {
            Facultades facu = Facultades.valueOf(nombre.toUpperCase());
            facultad.setFacultadName(facu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        facultadRepository.save(facultad);

    }


    private Permisos esPermiso(String permiso) {
        try {
            return Permisos.valueOf(permiso.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Permiso no encontrado");
        }
    }
}

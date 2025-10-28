package eci.edu.dows.profesorSuperO.Util.FactoryUsuariosLogin;

import eci.edu.dows.profesorSuperO.Util.Exceptions.NotFoundException;
import eci.edu.dows.profesorSuperO.Util.Mappers.DecanaturaMapper;
import eci.edu.dows.profesorSuperO.Util.Mappers.FacultadMapper;
import eci.edu.dows.profesorSuperO.model.Credencial;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioLoginDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin.UsuarioRegistroSalidaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.DecanaturaDTO;
import eci.edu.dows.profesorSuperO.model.Facultad;
import eci.edu.dows.profesorSuperO.model.Usuarios.Decanatura;
import eci.edu.dows.profesorSuperO.model.Usuarios.Usuario;
import eci.edu.dows.profesorSuperO.repository.CredencialRepository;
import eci.edu.dows.profesorSuperO.repository.FacultadRepository;
import eci.edu.dows.profesorSuperO.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class LoginDecanatura  implements LoginUsuario{

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final CredencialRepository credencialRepository;

    @Autowired
    private final DecanaturaMapper  decanaturaMapper;

    @Autowired
    private final FacultadRepository facultadRepository;

    @Autowired
    private final FacultadMapper facultadMapper;

    @Override
    public UsuarioLoginDTO loginUsuario(Usuario usuario) {
        Decanatura e = (Decanatura) usuario;
        UsuarioLoginDTO registroU = new UsuarioLoginDTO();
        registroU.setUsuario(e.getFullName());
        registroU.setRol(e.getPermiso().toString());
        registroU.setId(e.getId());
        registroU.setFacultad(e.getFacultad());

        return registroU;
    }

    @Override
    public UsuarioRegistroSalidaDTO registrarUsuario(UsuarioRegistroDTO uDTO){
        DecanaturaDTO decanoDto = new DecanaturaDTO();
        decanoDto.setCorreo(uDTO.getCorreo());
        decanoDto.setFullName(uDTO.getFullName());

        Facultad f = facultadRepository.findById(uDTO.getFacultad().getId()).orElseThrow(()-> new NotFoundException("facultad no encontrada"));

        FacultadDTO fDTO  = facultadMapper.toDTO(f);
        decanoDto.setPermiso(uDTO.getPermiso());
        decanoDto.setFacultadDTO(fDTO);

        Decanatura d = decanaturaMapper.toEntity(decanoDto);

        Decanatura dSalvado =  usuarioRepository.save(d);

        Credencial cred = new Credencial();
        cred.setUsuario(uDTO.getFullName());
        cred.setConstra(uDTO.getContra());
        cred.setUsuarioId(dSalvado.getId());

        UsuarioRegistroSalidaDTO salida  = new UsuarioRegistroSalidaDTO();
        salida.setUsuario(uDTO.getFullName());
        salida.setId(dSalvado.getId());
        salida.setRol(d.getPermiso().toString());

        credencialRepository.save(cred);
        return salida;
    }
}

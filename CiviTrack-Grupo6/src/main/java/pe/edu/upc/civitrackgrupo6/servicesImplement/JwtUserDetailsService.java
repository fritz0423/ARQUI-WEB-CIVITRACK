package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.dtos.UsuarioDtos;
import pe.edu.upc.civitrackgrupo6.entities.Rol;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.repositories.IRolRepository;
import pe.edu.upc.civitrackgrupo6.repositories.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = repo.findByCorreo(correo);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("El usuario con correo %s no existe", correo));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRol().getNombre()));

        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(),
                user.getPassword(),
                true, true, true, true,
                roles
        );
    }

    public boolean existsByCorreo(String correo) {
        return repo.existsByCorreo(correo);
    }

    public void save(UsuarioDtos dto) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword()); // ya viene encriptado
        // Buscar el objeto Rol por id
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);
        repo.save(usuario);
    }
}

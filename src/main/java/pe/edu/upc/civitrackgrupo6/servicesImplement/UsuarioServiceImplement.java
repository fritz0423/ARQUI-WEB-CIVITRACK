package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.repositories.IUsuarioRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUsuarioservice;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioservice {

    @Autowired
    private IUsuarioRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario insert(Usuario usuario) {

        if (uR.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setFechaRegistro(LocalDateTime.now());

        return uR.save(usuario);   // ← devuelve el usuario con su ID generado
    }

    @Override
    public void update(Usuario usuario) {

        Usuario existente = uR.findById(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Validar duplicado solo si cambia el correo
        if (!existente.getCorreo().equals(usuario.getCorreo()) &&
                uR.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado por otro usuario.");
        }

        // Encriptar password solo si fue cambiada
        if (!usuario.getPassword().equals(existente.getPassword())) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        uR.save(usuario);
    }

    @Override
    public void delete(int id) { uR.deleteById(id); }

    @Override
    public Usuario listId(int id) { return uR.findById(id).orElse(null); }

    @Override
    public List<Usuario> list() { return uR.findAll(); }

    @Override
    public Usuario findByCorreo(String correo) { return uR.findByCorreo(correo); }

    @Override
    public boolean existsByCorreo(String correo) { return uR.existsByCorreo(correo); }

}

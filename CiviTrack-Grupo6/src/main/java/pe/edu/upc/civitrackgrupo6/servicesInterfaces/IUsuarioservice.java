package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.dtos.ReporteRolDTO;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;

import java.util.List;

public interface IUsuarioservice {
    List<Usuario> list();
    Usuario insert(Usuario usuario);
    void delete(int id);
    Usuario listId(int id);
    void update(Usuario usuario);

    // US25 – Autenticación por correo
    Usuario findByCorreo(String correo);

    // US28 – Validar duplicado de correo
    boolean existsByCorreo(String correo);

}

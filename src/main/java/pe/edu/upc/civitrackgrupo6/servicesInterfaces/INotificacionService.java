package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Notificacion;
import java.time.LocalDateTime;
import java.util.List;

public interface INotificacionService {
    void insert(Notificacion notificacion);
    List<Notificacion> list();
    Notificacion listId(int id);
    void delete(int id);
    void update(Notificacion notificacion);

    //Querys
    List<Notificacion> findByUsuarioId(int idUsuario);
    List<Notificacion> findByFechaEnvio(LocalDateTime fecha);
}



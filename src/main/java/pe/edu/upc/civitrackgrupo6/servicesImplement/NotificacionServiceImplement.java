package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Notificacion;
import pe.edu.upc.civitrackgrupo6.repositories.INotificacionRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.INotificacionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImplement implements INotificacionService {

    @Autowired
    private INotificacionRepository nR;

    @Override
    public void insert(Notificacion notificacion) {
        nR.save(notificacion);
    }

    @Override
    public List<Notificacion> list() {
        return nR.findAll();
    }

    @Override
    public Notificacion listId(int id) {
        return nR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        nR.deleteById(id);
    }

    @Override
    public void update(Notificacion notificacion) {
        nR.save(notificacion);
    }

    @Override
    public List<Notificacion> findByUsuarioId(int idUsuario) {
        return nR.findByUsuarioId(idUsuario);
    }

    @Override
    public List<Notificacion> findByFechaEnvio(LocalDateTime fecha) {
        return nR.findByFechaEnvio(fecha);
    }
}

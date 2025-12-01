package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Ubicacion;
import pe.edu.upc.civitrackgrupo6.repositories.IUbicacionRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUbicacionService;

import java.util.List;

@Service
public class UbicacionServiceImplement implements IUbicacionService {

    @Autowired
    private IUbicacionRepository uR;

    @Override
    public void insert(Ubicacion ubicacion) {
        uR.save(ubicacion);
    }

    @Override
    public List<Ubicacion> list() {
        return uR.findAll();
    }

    @Override
    public Ubicacion listId(Integer id) {
        return uR.findById(id).orElse(null);
    }

    @Override
    public void update(Ubicacion u) {
        // Puedes validar si existe antes de actualizar
        if (uR.existsById(u.getIdUbicacion())) {
            uR.save(u);
        }
    }

    @Override
    public void delete(Integer id) {
        if (uR.existsById(id)) {
            uR.deleteById(id);
        }
    }

    @Override
    public List<Ubicacion> buscarPorCoordenadas(double latitud, double longitud) {
        return uR.buscarPorCoordenadas(latitud, longitud);
    }

}

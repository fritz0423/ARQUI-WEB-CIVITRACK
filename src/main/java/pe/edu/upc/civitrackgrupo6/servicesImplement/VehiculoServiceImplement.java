package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Vehiculo;
import pe.edu.upc.civitrackgrupo6.repositories.IVehiculoRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IVehiculoService;

import java.util.List;

@Service
public class VehiculoServiceImplement implements IVehiculoService {
    @Autowired
    private IVehiculoRepository vR;

    @Override
    public List<Vehiculo> list() {
        return vR.findAll();
    }

    @Override
    public Vehiculo insert(Vehiculo vehiculo) {
        return vR.save(vehiculo);   // devuelve el vehículo con ID generado
    }
    @Override
    public void delete(int id) {
        vR.deleteById(id);
    }

    @Override
    public Vehiculo listId(int id) {
        return vR.findById(id).orElse(null);
    }

    @Override
    public void update(Vehiculo vehiculo) {
        vR.save(vehiculo);
    }

    @Override
    public List<Vehiculo> findVehiculosActivos() {
        return vR.findVehiculosActivos();
    }

    @Override
    public List<Vehiculo> findVehiculosByConductor(int idConductor) {
        return vR.findVehiculosByConductor(idConductor);
    }

}

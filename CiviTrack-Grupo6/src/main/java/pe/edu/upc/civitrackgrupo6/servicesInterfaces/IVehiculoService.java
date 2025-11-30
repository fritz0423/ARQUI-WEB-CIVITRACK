package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Vehiculo;

import java.util.List;

public interface IVehiculoService {

    List<Vehiculo> list();
    Vehiculo insert(Vehiculo vehiculo);
    void delete(int id);
    Vehiculo listId(int id);
    void update(Vehiculo vehiculo);

    List<Vehiculo> findVehiculosActivos(); // US42
    List<Vehiculo> findVehiculosByConductor(int idConductor);
}

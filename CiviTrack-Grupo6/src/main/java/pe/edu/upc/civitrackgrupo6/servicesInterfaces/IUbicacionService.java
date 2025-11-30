package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Ubicacion;

import java.util.List;

public interface IUbicacionService {

    void insert(Ubicacion u);

    List<Ubicacion> list();

    Ubicacion listId(Integer id);

    void update(Ubicacion u);

    void delete(Integer id);

    // Query
    List<Ubicacion> buscarPorCoordenadas(double latitud, double longitud);
}


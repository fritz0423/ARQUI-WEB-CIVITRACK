package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Conductor;

import java.util.List;

public interface IConductorservice {
    List<Conductor> list();
    Conductor insert(Conductor conductor);
    void delete(int id);
    Conductor listId(int id);
    void update(Conductor conductor);
    List<Conductor> findTopConductores();
}
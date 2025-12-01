package pe.edu.upc.civitrackgrupo6.servicesInterfaces;


import pe.edu.upc.civitrackgrupo6.entities.Rol;

import java.util.List;

public interface IRolService {
    void insert(Rol rol);
    Rol listId(int id);
    void delete(int id);
    void update(Rol rol);
    List<Rol> list();
}

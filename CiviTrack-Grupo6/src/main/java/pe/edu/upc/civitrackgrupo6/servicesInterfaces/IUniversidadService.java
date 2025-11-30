package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Universidad;


import java.util.List;

public interface IUniversidadService {

    List<Universidad> list();
    void insert(Universidad universidad);
    void delete(int id);
    Universidad listId(int id);
    void update(Universidad universidad);

    boolean validarCorreoInstitucional(String correo); // US40
}

package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Rutas;

import java.util.List;

public interface IRutasservice {
    List<Rutas> list();
    void insert(Rutas rutas);
    void delete(int id);
    Rutas listId(int id);
    void update(Rutas rutas);

    List<Rutas> findRutasByConductor(Long idConductor); // US26
   /* List<Rutas> findRutasSeguras(); */                    // US27
    List<Object[]> rutasMasFrecuentes();
}

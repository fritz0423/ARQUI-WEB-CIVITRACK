package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.HistorialRuta;
import java.time.LocalDateTime;
import java.util.List;

public interface IHistorialRutaService {
    void insert(HistorialRuta historialRuta);
    List<HistorialRuta> list();
    HistorialRuta listId(int id);
    void delete(int id);
    void update(HistorialRuta historialRuta);

    // Querys
    List<HistorialRuta> findByRutaId(int idRuta);
    List<HistorialRuta> findByFecha(LocalDateTime inicio, LocalDateTime fin);
}


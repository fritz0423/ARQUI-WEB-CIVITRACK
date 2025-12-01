package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Validacion;

import java.util.List;

public interface IValidacionService {
    List<Validacion> list();
    Validacion insert(Validacion validacion);
    Validacion listId(int id);
    List<Validacion> findValidacionesPorReporte(Integer idReporte);
}

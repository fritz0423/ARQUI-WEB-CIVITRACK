package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Validacion;

import java.util.List;

@Repository
public interface IValidacionRepository extends JpaRepository<Validacion, Integer> {

    // Validaciones por el idReporte
    List<Validacion> findByReporteIdReporte(Integer idReporte);

}


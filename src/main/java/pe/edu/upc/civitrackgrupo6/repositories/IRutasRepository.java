package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Rutas;

import java.util.List;

@Repository
public interface IRutasRepository extends JpaRepository<Rutas,Integer> {
    // US26 – Rutas por conductor autenticado
    @Query("SELECT r FROM Rutas r WHERE r.usuario.id = :idConductor")
    List<Rutas> findRutasByConductor(@Param("idConductor") Long idConductor);

    // US27 – Rutas seguras (subquery con reportes)
  /*  @Query("SELECT r FROM Rutas r WHERE r.idruta NOT IN (SELECT re.ruta.idruta FROM Reporte re GROUP BY re.ruta.idruta HAVING COUNT(re.idReporte) >= 2)")
    List<Rutas> findRutasSeguras();*/

    // US39 – Rutas más frecuentes (sin paginación)
    @Query("SELECT CONCAT(r.latituddestino, ',', r.longituddestino) AS destino, COUNT(r.idruta) AS frecuencia " +
            "FROM Rutas r GROUP BY r.latituddestino, r.longituddestino ORDER BY frecuencia DESC")
    List<Object[]> rutasMasFrecuentes(); // ← sin Pageable

}

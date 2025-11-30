package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Conductor;

import java.util.List;

@Repository
public interface IConductorRepository extends JpaRepository<Conductor,Integer> {

    // US29 – Conductores mejor calificados
    @Query("SELECT c FROM Conductor c ORDER BY c.promedioCalificacion DESC")
    List<Conductor> findTopConductores();

}

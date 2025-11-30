package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Vehiculo;

import java.util.List;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    // US42 – Vehículos activos
    @Query("SELECT v FROM Vehiculo v WHERE v.estado = 'Activo'")
    List<Vehiculo> findVehiculosActivos();

    // US44 – Vehículos por conductor
    @Query("SELECT v FROM Vehiculo v WHERE v.conductor.id = :idConductor")
    List<Vehiculo> findVehiculosByConductor(@Param("idConductor") int idConductor);

}

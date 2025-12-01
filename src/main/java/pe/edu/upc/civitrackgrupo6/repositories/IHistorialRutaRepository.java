package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.HistorialRuta;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IHistorialRutaRepository extends JpaRepository<HistorialRuta, Integer> {

    // Query 1: Buscar historial por ruta
    @Query("SELECT h FROM HistorialRuta h WHERE h.ruta.idruta = :idRuta")
    List<HistorialRuta> findByRutaId(@Param("idRuta") int idRuta);

    // Query 2: Buscar historial por rango de fechas
    @Query("SELECT h FROM HistorialRuta h WHERE h.fecha BETWEEN :inicio AND :fin")
    List<HistorialRuta> findByFecha(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}

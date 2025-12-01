package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Notificacion;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {

    // Query 1: Buscar notificaciones por usuario
    @Query("SELECT n FROM Notificacion n WHERE n.usuario.idUsuario = :idUsuario")
    List<Notificacion> findByUsuarioId(@Param("idUsuario") int idUsuario);

    // Query 2: Buscar notificaciones en una fecha
    @Query("SELECT n FROM Notificacion n WHERE n.fechaEnvio >= :fecha")
    List<Notificacion> findByFechaEnvio(@Param("fecha") LocalDateTime fecha);
}

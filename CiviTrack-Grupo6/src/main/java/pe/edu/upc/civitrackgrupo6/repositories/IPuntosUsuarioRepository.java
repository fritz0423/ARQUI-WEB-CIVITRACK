package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.PuntosUsuario;

import java.util.List;
@Repository
public interface IPuntosUsuarioRepository extends JpaRepository<PuntosUsuario,Integer> {
    // US45 – Total de puntos por usuario
    @Query("SELECT SUM(p.puntosTotal) FROM PuntosUsuario p WHERE p.usuario.id = :idUsuario")
    Integer totalPuntosPorUsuario(@Param("idUsuario") Long idUsuario);

    // US46 – Ranking de usuarios (sin paginación)
    @Query("SELECT p.usuario.nombre, SUM(p.puntosTotal) AS total " +
            "FROM PuntosUsuario p GROUP BY p.usuario.nombre ORDER BY total DESC")
    List<Object[]> rankingUsuarios(); // ← sin Pageable

    // Retorna la cantidad de reportes de un usuario
    // 🔹 Contar reportes por usuario
    @Query("SELECT COUNT(p) FROM PuntosUsuario p WHERE p.usuario.id = :idUsuario")
    Integer countByUsuarioId(@Param("idUsuario") Long idUsuario);

}

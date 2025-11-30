package pe.edu.upc.civitrackgrupo6.repositories;
import pe.edu.upc.civitrackgrupo6.dtos.ReporteRolDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IReporteRepository extends JpaRepository<Reporte, Integer> {

    // US30 – Reportes por fecha
    @Query("SELECT r FROM Reporte r WHERE r.fechaHora BETWEEN :inicio AND :fin ORDER BY r.fechaHora DESC")
    List<Reporte> findReportesPorFecha(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // US31 – Reportes por zona (query nativa con distancia)
    @Query(value = "SELECT r.* " +
            "FROM reporte r " +
            "JOIN ubicacion u ON r.id_ubicacion = u.id_ubicacion " +
            "WHERE u.latitud IS NOT NULL AND u.longitud IS NOT NULL " +
            "AND (6371 * acos( " +
            "  cos(radians(:lat)) * cos(radians(u.latitud)) * " +
            "  cos(radians(u.longitud) - radians(:lng)) + " +
            "  sin(radians(:lat)) * sin(radians(u.latitud)) " +
            ")) <= :radio",
            nativeQuery = true)
    List<Reporte> findReportesPorZona(@Param("lat") double lat,
                                      @Param("lng") double lng,
                                      @Param("radio") double radio);

    // US32 – Validar coherencia ubicación–categoría
    @Query("SELECT r FROM Reporte r " +
            "WHERE EXISTS (" +
            "  SELECT 1 FROM Reporte r2 " +
            "  WHERE r.ubicacion.latitud = r2.ubicacion.latitud " +
            "    AND r.ubicacion.longitud = r2.ubicacion.longitud " +
            "    AND r.categoria.idCategoria <> r2.categoria.idCategoria" +
            ")")
    List<Reporte> findReportesInconsistentes();


    // US33 – Estadísticas por categoría
    @Query("SELECT r.categoria.nombre AS categoria, COUNT(r) AS total " +
            "FROM Reporte r GROUP BY r.categoria.nombre ORDER BY total DESC")
    List<Object[]> estadisticasPorCategoria();

    // US34 – Categorías más utilizadas
    @Query(value = "SELECT c.nombre, COUNT(r.id_categoria) AS cantidad " +
            "FROM reporte r JOIN categoria c ON r.id_categoria = c.id_categoria " +
            "GROUP BY c.nombre " +
            "ORDER BY cantidad DESC LIMIT 3", nativeQuery = true)
    List<Object[]> findTopCategorias();


    // USXX – Cantidad de reportes por rol
    @Query("SELECT r.usuario.rol.nombre AS rol, COUNT(r) AS total " +
            "FROM Reporte r GROUP BY r.usuario.rol.nombre ORDER BY total DESC")
    List<Object[]> cantidadReportesPorRol();


}

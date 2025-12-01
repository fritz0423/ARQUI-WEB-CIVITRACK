package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.dtos.ReporteRolDTO;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;

import java.time.LocalDate;
import java.util.List;



public interface IReporteService {
    // Collection<Object> list();

    public List<Reporte> list();
    void insert(Reporte reporte);

    Reporte listId(int id);

    void delete(int id);

    void update(Reporte re);

    // US30
    List<Reporte> findReportesPorFecha(LocalDate inicio, LocalDate fin);

    // US31
    List<Reporte> findReportesPorZona(double lat, double lng, double radio);

    // US32
    List<Reporte> findReportesInconsistentes();

    // US33
    List<Object[]> estadisticasPorCategoria();

    // US34
    List<Object[]> findTopCategorias();

    // USXX – Reportes por rol
    List<ReporteRolDTO> cantidadReportesPorRol();

}

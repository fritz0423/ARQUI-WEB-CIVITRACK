package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.dtos.ReporteRolDTO;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;
import pe.edu.upc.civitrackgrupo6.repositories.IReporteRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IReporteService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImplement implements IReporteService {

    @Autowired
    private IReporteRepository sR;

    @Override
    public void insert(Reporte reporte) {
        sR.save(reporte);
    }

    @Override
    public Reporte listId(int id) {
        return sR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        if (sR.existsById(id)) {
            sR.deleteById(id);
        }
    }

    @Override
    public void update(Reporte reporte) {
        sR.save(reporte);
    }

    @Override
    public List<Reporte> list() {
        return sR.findAll();
    }


    @Override
    public List<Reporte> findReportesPorFecha(LocalDate inicio, LocalDate fin) {
        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime finDateTime = fin.atTime(LocalTime.MAX); // incluye toda la fecha final
        return sR.findReportesPorFecha(inicioDateTime, finDateTime);
    }

    @Override
    public List<Reporte> findReportesInconsistentes() {
        return sR.findReportesInconsistentes();
    }

    @Override
    public List<Object[]> estadisticasPorCategoria() {
        return sR.estadisticasPorCategoria();
    }

    @Override
    public List<Object[]> findTopCategorias() {
        return sR.findTopCategorias();
    }

    @Override
    public List<Reporte> findReportesPorZona(double lat, double lng, double radio) {
        return sR.findReportesPorZona(lat, lng, radio);
    }

    @Override
    public List<ReporteRolDTO> cantidadReportesPorRol() {
        List<Object[]> resultados = sR.cantidadReportesPorRol();

        List<ReporteRolDTO> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            String rol = (String) fila[0];
            Long cantidad = (Long) fila[1];

            lista.add(new ReporteRolDTO(rol, cantidad));
        }

        return lista;
    }

}

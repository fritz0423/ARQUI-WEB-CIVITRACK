package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.entities.Validacion;
import pe.edu.upc.civitrackgrupo6.repositories.IValidacionRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IValidacionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidacionServiceImplement implements IValidacionService {

    @Autowired
    private IValidacionRepository vR;

    @Override
    public List<Validacion> list() {
        return vR.findAll();
    }

    @Override
    public Validacion insert(Validacion validacion) {
        // fuerza fecha actual si no viene
        if (validacion.getFechaValidacion() == null) {
            validacion.setFechaValidacion(LocalDateTime.now());
        }

        // estado por defecto si no viene
        if (validacion.getEstado() == null || validacion.getEstado().isBlank()) {
            validacion.setEstado("VALIDADO");
        }
        return vR.save(validacion);
    }

    @Override
    public Validacion listId(int id) {
        return vR.findById(id).orElse(null);
    }

    @Override
    public List<Validacion> findValidacionesPorReporte(Integer idReporte) {
        return vR.findByReporteIdReporte(idReporte);
    }
}

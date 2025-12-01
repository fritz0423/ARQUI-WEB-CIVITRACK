package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.HistorialRuta;
import pe.edu.upc.civitrackgrupo6.repositories.IHistorialRutaRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IHistorialRutaService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialRutaServiceImplement implements IHistorialRutaService {

    @Autowired
    private IHistorialRutaRepository hR;

    @Override
    public void insert(HistorialRuta historialRuta) {
        hR.save(historialRuta);
    }

    @Override
    public List<HistorialRuta> list() {
        return hR.findAll();
    }

    @Override
    public HistorialRuta listId(int id) {
        return hR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        hR.deleteById(id);
    }

    @Override
    public void update(HistorialRuta historialRuta) {
        hR.save(historialRuta);
    }

    @Override
    public List<HistorialRuta> findByRutaId(int idRuta) {
        return hR.findByRutaId(idRuta);
    }

    @Override
    public List<HistorialRuta> findByFecha(LocalDateTime inicio, LocalDateTime fin) {
        return hR.findByFecha(inicio, fin);
    }
}

package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Universidad;
import pe.edu.upc.civitrackgrupo6.repositories.IUniversidadRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUniversidadService;

import java.util.List;


@Service
public class UniversidadServiceImplement implements IUniversidadService {
    @Autowired
    private IUniversidadRepository uR;

    @Override
    public List<Universidad> list() {
        return uR.findAll();
    }

    @Override
    public void insert(Universidad universidad) {
        uR.save(universidad);
    }

    @Override
    public void delete(int id) {
        if (uR.existsById(id)) {
            uR.deleteById(id);
        }
    }

    @Override
    public Universidad listId(int id) {
        return uR.findById(id).orElse(null);
    }

    @Override
    public void update(Universidad universidad) {
        uR.save(universidad);
    }

    @Override
    public boolean validarCorreoInstitucional(String correo) {
        return uR.validarCorreoInstitucional(correo);
    }

}

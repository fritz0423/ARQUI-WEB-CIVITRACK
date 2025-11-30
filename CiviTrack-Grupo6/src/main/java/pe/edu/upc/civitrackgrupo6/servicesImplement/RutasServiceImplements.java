package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Rutas;
import pe.edu.upc.civitrackgrupo6.repositories.IRutasRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IRutasservice;

import java.util.List;

@Service
public class RutasServiceImplements implements IRutasservice {
    @Autowired
    private IRutasRepository iR;


    @Override
    public List<Rutas> list() {
        return iR.findAll();
    }

    @Override
    public void insert(Rutas usuario) {
        iR.save(usuario);

    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);

    }

    @Override
    public Rutas listId(int id) {
        return iR.findById(id).orElse(null);
    }

    @Override
    public void update(Rutas usuario) {

        iR.save(usuario);

    }
    @Override
    public List<Rutas> findRutasByConductor(Long idConductor) {
        return iR.findRutasByConductor(idConductor);
    }

   /* @Override
    public List<Rutas> findRutasSeguras() {
        return iR.findRutasSeguras();
    }*/

    @Override
    public List<Object[]> rutasMasFrecuentes() {
        return iR.rutasMasFrecuentes();
    }

}

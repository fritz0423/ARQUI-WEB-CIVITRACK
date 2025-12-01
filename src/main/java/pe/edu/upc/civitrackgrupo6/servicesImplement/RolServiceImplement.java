package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Rol;
import pe.edu.upc.civitrackgrupo6.repositories.IRolRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IRolService;

import java.util.List;

@Service

public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository rR;

    @Override
    public List<Rol> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Rol rol) {
        rR.save(rol);
    }

    @Override
    public void delete(int id) {
        long idLong = id;
        if (rR.existsById(idLong)) {
            rR.deleteById(idLong);
        }
    }

    @Override
    public Rol listId(int id) {
        return rR.findById((long) id).orElse(null);
    }

    @Override
    public void update(Rol rol) {
        rR.save(rol);
    }

}

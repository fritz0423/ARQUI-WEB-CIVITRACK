package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Conductor;
import pe.edu.upc.civitrackgrupo6.repositories.IConductorRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IConductorservice;

import java.util.List;

@Service
public class ConductorServiceImplements implements IConductorservice {

    @Autowired
    private IConductorRepository cR;


    @Override
    public List<Conductor> list() {
        return cR.findAll();
    }

    @Override
    public Conductor insert(Conductor conductor) {
        return cR.save(conductor);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public Conductor listId(int id) {
        return cR.findById(id).orElse(null);
    }
    @Override
    public void update(Conductor conductor) {
        cR.save(conductor);

    }
    @Override
    public List<Conductor> findTopConductores() {
        return cR.findTopConductores();
    }

}

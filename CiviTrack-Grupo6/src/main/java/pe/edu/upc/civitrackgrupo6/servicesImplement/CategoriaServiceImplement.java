package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Categoria;
import pe.edu.upc.civitrackgrupo6.repositories.ICategoriaRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.ICategoriaService;

import java.util.List;


@Service
public class CategoriaServiceImplement implements ICategoriaService {

    @Autowired
    private ICategoriaRepository cR;

    @Override
    public void insert(Categoria c) {
        cR.save(c);
    }

    @Override
    public List<Categoria> list() {
        return cR.findAll();
    }



    @Override
    public void delete(Integer id) {
        if (cR.existsById(id)) {
            cR.deleteById(id);
        }
    }

    @Override
    public Categoria listId(int id) {
        return cR.findById(id).orElse(null);
    }

    @Override
    public void update(Categoria categoria) {
        cR.save(categoria);
    }

    @Override
    public List<Categoria> buscarPorNombre(String nombre) {
        return cR.buscarPorNombre(nombre);
    }
}


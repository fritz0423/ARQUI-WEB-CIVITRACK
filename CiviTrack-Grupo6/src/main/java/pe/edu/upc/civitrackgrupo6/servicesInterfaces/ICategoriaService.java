package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Categoria;

import java.util.List;

public interface ICategoriaService {
    //  Collection<Object> list();

    void insert(Categoria c);
    List<Categoria> list();

    void delete(Integer id);
    List<Categoria> buscarPorNombre(String nombre);

    Categoria listId(int id);
    void update(Categoria categoria);
}

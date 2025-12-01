package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Categoria;

import java.util.List;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria,Integer>{
    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Categoria> buscarPorNombre(@Param("nombre") String nombre);
}

package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);

}

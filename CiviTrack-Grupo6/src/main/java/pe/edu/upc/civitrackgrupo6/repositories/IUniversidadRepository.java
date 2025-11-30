package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Universidad;

import java.util.List;

@Repository
public interface IUniversidadRepository extends JpaRepository<Universidad, Integer> {

    // US40 – Validar correo institucional
    @Query("SELECT COUNT(u) > 0 FROM Universidad u WHERE :correo LIKE CONCAT('%', u.dominio)")
    boolean validarCorreoInstitucional(@Param("correo") String correo);

}

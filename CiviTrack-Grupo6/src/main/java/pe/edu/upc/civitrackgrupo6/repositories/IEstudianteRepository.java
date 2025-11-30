package pe.edu.upc.civitrackgrupo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.civitrackgrupo6.entities.Estudiante;

import java.util.List;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Integer> {
    // US41 – Estudiantes por universidad
    @Query("SELECT e FROM Estudiante e JOIN e.universidad u WHERE u.idUniversidad = :idUniversidad")
    List<Estudiante> findEstudiantesPorUniversidad(@Param("idUniversidad") Long idUniversidad);

    Estudiante findByCodigoEstudiante(String codigoEstudiante);
    List<Estudiante> findByCarreraEstudiante(String carreraEstudiante);

}

package pe.edu.upc.civitrackgrupo6.servicesInterfaces;

import pe.edu.upc.civitrackgrupo6.entities.Estudiante;

import java.util.List;

public interface IEstudianteService {

    List<Estudiante> list();
    Estudiante insert(Estudiante estudiante);
    void delete(int id);
    Estudiante listId(int id);
    void update(Estudiante estudiante);

    List<Estudiante> findEstudiantesPorUniversidad(Long idUniversidad); // US41

    // Opcional: solo si los agregas al repositorio
    Estudiante findByCodigoEstudiante(String codigoEstudiante);
    List<Estudiante> findByCarreraEstudiante(String carreraEstudiante);
}

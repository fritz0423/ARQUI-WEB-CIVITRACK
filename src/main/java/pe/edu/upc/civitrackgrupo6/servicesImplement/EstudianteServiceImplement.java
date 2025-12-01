package pe.edu.upc.civitrackgrupo6.servicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.Estudiante;
import pe.edu.upc.civitrackgrupo6.repositories.IEstudianteRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IEstudianteService;

import java.util.List;

@Service
public class EstudianteServiceImplement implements IEstudianteService {
    @Autowired
    private IEstudianteRepository eR;

    @Override
    public List<Estudiante> list() {
        return eR.findAll();
    }

    @Override
    public Estudiante insert(Estudiante estudiante) {
        return eR.save(estudiante);
    }
    @Override
    public void delete(int id) {
        eR.deleteById(id);
    }

    @Override
    public Estudiante listId(int id) {
        return eR.findById(id).orElse(null);
    }

    @Override
    public void update(Estudiante estudiante) {
        eR.save(estudiante);
    }

    @Override
    public List<Estudiante> findEstudiantesPorUniversidad(Long idUniversidad) {
        return eR.findEstudiantesPorUniversidad(idUniversidad);
    }

    // Opcional: solo si agregas estos métodos al repositorio
    @Override
    public Estudiante findByCodigoEstudiante(String codigoEstudiante) {
        return eR.findByCodigoEstudiante(codigoEstudiante);
    }

    @Override
    public List<Estudiante> findByCarreraEstudiante(String carreraEstudiante) {
        return eR.findByCarreraEstudiante(carreraEstudiante);
    }

}

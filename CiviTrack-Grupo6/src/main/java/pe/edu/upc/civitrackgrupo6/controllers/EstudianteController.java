package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.EstudianteDTO;
import pe.edu.upc.civitrackgrupo6.entities.Estudiante;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IEstudianteService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private IEstudianteService eS;
    
    @GetMapping
    public List<EstudianteDTO> listar() {
        return eS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EstudianteDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody EstudianteDTO dto) {
        ModelMapper m = new ModelMapper();
        Estudiante est = m.map(dto, Estudiante.class);

        Estudiante guardado = eS.insert(est);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(m.map(guardado, EstudianteDTO.class));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Estudiante est = eS.listId(id);
        if (est == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un estudiante con el ID: " + id);
        }
        eS.delete(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Estudiante est = eS.listId(id);
        if (est == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un estudiante con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EstudianteDTO dto = m.map(est, EstudianteDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody EstudianteDTO dto) {
        ModelMapper m = new ModelMapper();
        Estudiante r = m.map(dto, Estudiante.class);

        Estudiante existente = eS.listId(r.getIdEstudiante());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un Estudiante con el ID: " + r.getIdEstudiante());
        }
        eS.update(r);
        return ResponseEntity.ok("Estudiante actualizado correctamente.");
    }
    @GetMapping("/universidad/{id}")
    public ResponseEntity<?> listarPorUniversidad(@PathVariable("id") Long idUniversidad) {
        List<EstudianteDTO> lista = eS.findEstudiantesPorUniversidad(idUniversidad).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EstudianteDTO.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No hay estudiantes registrados en la universidad con ID: " + idUniversidad);
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/carrera/{carrera}")
    public ResponseEntity<?> buscarPorCarrera(@PathVariable("carrera") String carrera) {
        List<EstudianteDTO> lista = eS.findByCarreraEstudiante(carrera).stream()
                .map(x -> new ModelMapper().map(x, EstudianteDTO.class))
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No hay estudiantes registrados en la carrera: " + carrera);
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable("codigo") String codigo) {
        Estudiante est = eS.findByCodigoEstudiante(codigo);
        if (est == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un estudiante con el código: " + codigo);
        }
        ModelMapper m = new ModelMapper();
        EstudianteDTO dto = m.map(est, EstudianteDTO.class);
        return ResponseEntity.ok(dto);
    }
}

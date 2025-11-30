package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.HistorialRutaDTO;
import pe.edu.upc.civitrackgrupo6.entities.HistorialRuta;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IHistorialRutaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historialrutas")
public class HistorialRutaController {

    @Autowired
    private IHistorialRutaService hrS;

    @GetMapping
    public List<HistorialRutaDTO> listar() {
        return hrS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, HistorialRutaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody HistorialRutaDTO dto) {
        ModelMapper m = new ModelMapper();
        HistorialRuta hr = m.map(dto, HistorialRuta.class);
        hrS.insert(hr);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        HistorialRuta historial = hrS.listId(id);
        if (historial == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe historial con el ID: " + id);
        }
        hrS.delete(id);
        return ResponseEntity.ok("Historial eliminado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        HistorialRuta historial = hrS.listId(id);
        if (historial == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe historial con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        HistorialRutaDTO dto = m.map(historial, HistorialRutaDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody HistorialRutaDTO dto) {
        ModelMapper m = new ModelMapper();
        HistorialRuta hr = m.map(dto, HistorialRuta.class);

        HistorialRuta existente = hrS.listId(hr.getIdHistorialRuta());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe historial con el ID: " + hr.getIdHistorialRuta());
        }
        hrS.update(hr);
        return ResponseEntity.ok("Historial modificado correctamente.");
    }

    //Query 1
    @GetMapping("/ruta/{idRuta}")
    public List<HistorialRutaDTO> listarPorRuta(@PathVariable("idRuta") int idRuta) {
        return hrS.findByRutaId(idRuta).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, HistorialRutaDTO.class);
        }).collect(Collectors.toList());
    }

    //Query 2
    @GetMapping("/fechas")
    public List<HistorialRutaDTO> listarPorFechas(@RequestParam("inicio") String inicio, @RequestParam("fin") String fin) {
        LocalDateTime fechaInicio = LocalDateTime.parse(inicio);
        LocalDateTime fechaFin = LocalDateTime.parse(fin);
        return hrS.findByFecha(fechaInicio, fechaFin).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, HistorialRutaDTO.class);
        }).collect(Collectors.toList());
    }


}
package pe.edu.upc.civitrackgrupo6.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.UbicacionDTO;
import pe.edu.upc.civitrackgrupo6.entities.Ubicacion;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUbicacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    private IUbicacionService uS;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<UbicacionDTO> lista = uS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x,UbicacionDTO.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No existen ubicaciones registrados.");
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody UbicacionDTO dto) {
        ModelMapper m = new ModelMapper();
        Ubicacion u = m.map(dto, Ubicacion.class);
        uS.insert(u);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ubicacion registrado correctamente.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Ubicacion ubicacion = uS.listId(id);
        if (ubicacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una ubicación con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UbicacionDTO dto = m.map(ubicacion, UbicacionDTO.class);
        return ResponseEntity.ok(dto);
    }
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UbicacionDTO dto) {
        ModelMapper m = new ModelMapper();
        Ubicacion u = m.map(dto, Ubicacion.class);

        Ubicacion existente = uS.listId(u.getIdUbicacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe una ubicación con el ID: " + u.getIdUbicacion());
        }
        uS.update(u);
        return ResponseEntity.ok("Ubicación modificada correctamente.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Ubicacion ubicacion = uS.listId(id);
        if (ubicacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una ubicación con el ID: " + id);
        }
        uS.delete(id);
        return ResponseEntity.ok("Ubicación eliminada correctamente.");
    }




    @GetMapping("/coordenadas")
    public ResponseEntity<?> buscarPorCoordenadas(@RequestParam("latitud") double latitud,
                                                  @RequestParam("longitud") double longitud) {
        List<UbicacionDTO> lista = uS.buscarPorCoordenadas(latitud, longitud).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UbicacionDTO.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No se encontraron ubicaciones en las coordenadas proporcionadas.");
        }

        return ResponseEntity.ok(lista);
    }

}

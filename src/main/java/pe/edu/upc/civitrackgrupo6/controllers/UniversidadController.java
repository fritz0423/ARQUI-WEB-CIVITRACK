package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.civitrackgrupo6.dtos.UniversidadDTO;
import pe.edu.upc.civitrackgrupo6.entities.Universidad;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUniversidadService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {
    @Autowired
    private IUniversidadService uS;

    @GetMapping
    public List<UniversidadDTO> listar() {
        return uS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UniversidadDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UniversidadDTO dto) {
        ModelMapper m = new ModelMapper();
        Universidad r = m.map(dto, Universidad.class);
        uS.insert(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Universidad uni = uS.listId(id);
        if (uni == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una universidad con el ID: " + id);
        }
        uS.delete(id);
        return ResponseEntity.ok("Universidad eliminada correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Universidad uni = uS.listId(id);
        if (uni == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una universidad con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UniversidadDTO dto = m.map(uni, UniversidadDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UniversidadDTO dto) {
        ModelMapper m = new ModelMapper();
        Universidad r = m.map(dto, Universidad.class);

        Universidad existente = uS.listId(r.getIdUniversidad());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una universidad con el ID: " + r.getIdUniversidad());
        }
        uS.update(r);
        return ResponseEntity.ok("Universidad actualizada correctamente.");
    }

    @GetMapping("/validar-correoUni")
    public ResponseEntity<?> validarCorreo(@RequestParam("correo") String correo) {
        boolean esValido = uS.validarCorreoInstitucional(correo);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("correo", correo);
        respuesta.put("valido", esValido);
        return ResponseEntity.ok(respuesta);
    }

}

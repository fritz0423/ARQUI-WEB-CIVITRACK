package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.ConductorDtos;
import pe.edu.upc.civitrackgrupo6.entities.Conductor;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IConductorservice;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUsuarioservice;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conductores")
public class ConductorControllers {

    @Autowired
    private IConductorservice cS;

    @Autowired
    private IUsuarioservice uS;


    @GetMapping
    public List<ConductorDtos> listar() {
        return cS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ConductorDtos.class);
        }).collect(Collectors.toList());
    }


    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody ConductorDtos dto) {
        ModelMapper m = new ModelMapper();
        Conductor conductor = m.map(dto, Conductor.class);

        // Buscar el usuario
        Usuario usuario = uS.listId(dto.getIdUsuario());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + dto.getIdUsuario());
        }

        conductor.setUsuario(usuario);

        Conductor guardado = cS.insert(conductor);
        ConductorDtos respuesta = m.map(guardado, ConductorDtos.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
        Conductor conductor = cS.listId(id);

        if (conductor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        cS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") int id) {
        Conductor conductor = cS.listId(id);

        if (conductor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        ConductorDtos dto = m.map(conductor, ConductorDtos.class);

        return ResponseEntity.ok(dto);
    }


    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ConductorDtos dto) {
        ModelMapper m = new ModelMapper();
        Conductor nuevo = m.map(dto, Conductor.class);

        // Buscar conductor existente
        Conductor existente = cS.listId(dto.getIdConductor());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un conductor con el ID: " + dto.getIdConductor());
        }

        // No permitir cambiar el usuario vinculado
        nuevo.setUsuario(existente.getUsuario());

        cS.update(nuevo);

        return ResponseEntity.ok("Conductor con ID " + dto.getIdConductor() + " modificado correctamente.");
    }


    @GetMapping("/top")
    public ResponseEntity<?> listarTopConductores() {
        List<ConductorDtos> lista = cS.findTopConductores().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ConductorDtos.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
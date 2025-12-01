package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.VehiculoDTO;
import pe.edu.upc.civitrackgrupo6.entities.Conductor;
import pe.edu.upc.civitrackgrupo6.entities.Vehiculo;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IConductorservice;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IVehiculoService;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService vS;

    @Autowired
    private IConductorservice cS;

    @GetMapping
    public List<VehiculoDTO> listar() {
        return vS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, VehiculoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody VehiculoDTO dto) {

        ModelMapper m = new ModelMapper();
        Vehiculo vehiculo = m.map(dto, Vehiculo.class);

        // Convertir INT → LONG correctamente
        int idConductor = dto.getIdConductor();
        Conductor conductor = cS.listId(idConductor);
        if (conductor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un conductor con el ID: " + dto.getIdConductor());
        }

        vehiculo.setConductor(conductor);

        Vehiculo creado = vS.insert(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Vehiculo veh = vS.listId(id);
        if (veh == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un vehículo con el ID: " + id);
        }
        vS.delete(id);
        return ResponseEntity.ok("Vehículo eliminado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Vehiculo veh = vS.listId(id);
        if (veh == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un vehículo con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        VehiculoDTO dto = m.map(veh, VehiculoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody VehiculoDTO dto) {

        Vehiculo existente = vS.listId(dto.getIdVehiculo());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un vehículo con el ID: " + dto.getIdVehiculo());
        }

        ModelMapper m = new ModelMapper();
        Vehiculo vehiculo = m.map(dto, Vehiculo.class);

        // Obtener el ID del conductor como INT
        int idConductor = dto.getIdConductor();

        // Buscar conductor por INT
        Conductor conductor = cS.listId(idConductor);
        if (conductor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un conductor con el ID: " + dto.getIdConductor());
        }

        vehiculo.setConductor(conductor);

        vS.update(vehiculo);
        return ResponseEntity.ok("Vehículo actualizado correctamente.");
    }

    @GetMapping("/activos")
    public ResponseEntity<?> listarVehiculosActivos() {
        List<VehiculoDTO> lista = vS.findVehiculosActivos().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, VehiculoDTO.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.ok("No hay vehículos activos registrados.");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/conductor/{idConductor}")
    public ResponseEntity<?> listarPorConductor(@PathVariable("idConductor") int idConductor) {

        List<VehiculoDTO> lista = vS.findVehiculosByConductor(idConductor)
                .stream().map(x -> new ModelMapper().map(x, VehiculoDTO.class))
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.ok("No hay vehículos registrados para el conductor con ID: " + idConductor);
        }

        return ResponseEntity.ok(lista);
    }
}

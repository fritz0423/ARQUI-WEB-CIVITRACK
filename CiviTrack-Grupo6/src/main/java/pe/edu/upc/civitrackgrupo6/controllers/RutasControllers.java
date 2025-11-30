package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.RutasDtos;
import pe.edu.upc.civitrackgrupo6.entities.Rutas;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IRutasservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Rutas")
public class RutasControllers {
    @Autowired
    private IRutasservice rS;

    @GetMapping
    public List<RutasDtos> listar(){
        return rS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,RutasDtos.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RutasDtos dto){
        ModelMapper m = new ModelMapper();
        Rutas a = m.map(dto, Rutas.class);
        rS.insert(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Rutas rutas = rS.listId(id);
        if (rutas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Rutas rutas= rS.listId(id);
        if (rutas == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RutasDtos dto = m.map(rutas, RutasDtos.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RutasDtos dto) {
        ModelMapper m = new ModelMapper();
        Rutas ar = m.map(dto, Rutas.class);

        Rutas existente = rS.listId(ar.getIdruta());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + ar.getIdruta());
        }
        rS.update(ar);
        return ResponseEntity.ok("Registro con ID " + ar.getIdruta() + " modificado correctamente.");
    }

    @GetMapping("/conductor/{idConductor}")
    public ResponseEntity<?> listarPorConductor(@PathVariable("idConductor") Long idConductor) {
        List<RutasDtos> lista = rS.findRutasByConductor(idConductor).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RutasDtos.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No hay rutas registradas para el conductor con ID: " + idConductor);
        }

        return ResponseEntity.ok(lista);
    }

 /*   @GetMapping("/seguras")
    public ResponseEntity<?> listarRutasSeguras() {
        List<RutasDtos> lista = rS.findRutasSeguras().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RutasDtos.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }*/

    @GetMapping("/frecuentes")
    public ResponseEntity<?> rutasMasFrecuentes() {
        List<Object[]> datos = rS.rutasMasFrecuentes();

        List<Map<String, Object>> respuesta = datos.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("destino", obj[0]);
            map.put("frecuencia", obj[1]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }

}

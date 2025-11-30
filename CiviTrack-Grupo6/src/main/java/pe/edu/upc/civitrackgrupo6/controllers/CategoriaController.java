package pe.edu.upc.civitrackgrupo6.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.CategoriaDTO;
import pe.edu.upc.civitrackgrupo6.entities.Categoria;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.ICategoriaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
//esteeee REGISTRO BACK
    @Autowired
    private ICategoriaService cS;


    @GetMapping
    public ResponseEntity<?> listar() {
        List<CategoriaDTO> lista = cS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CategoriaDTO.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No existen categorias registrados.");
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody CategoriaDTO dto) {
        ModelMapper m = new ModelMapper();
        Categoria c = m.map(dto, Categoria.class);
        cS.insert(c);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Categoria registrado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@RequestBody CategoriaDTO dto) {
        ModelMapper m = new ModelMapper();
        Categoria categoria = m.map(dto, Categoria.class);

        // Verificar si la categoría existe usando el ID del DTO
        Categoria existente = cS.listId(categoria.getIdCategoria());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe una categoría con el ID: " + categoria.getIdCategoria());
        }

        // Actualizar categoría existente
        cS.update(categoria);
        return ResponseEntity.ok("Categoría con ID " + categoria.getIdCategoria() + " modificada correctamente.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
        return ResponseEntity.ok("Categoría eliminada correctamente.");
    }


    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam("nombre") String nombre) {
        List<CategoriaDTO> lista = cS.buscarPorNombre(nombre).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CategoriaDTO.class);
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No se encontraron categorías con ese nombre.");
        }
        return ResponseEntity.ok(lista);
    }


}
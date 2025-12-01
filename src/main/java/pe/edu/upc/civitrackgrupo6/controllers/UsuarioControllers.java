package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.UsuarioDtos;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IUsuarioservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/usuarios")
public class UsuarioControllers {

    @Autowired
    private IUsuarioservice uS;

    @GetMapping
    public List<UsuarioDtos> listar() {
        return uS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDtos.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody UsuarioDtos dto) {

        if (uS.existsByCorreo(dto.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo ya está registrado.");
        }

        ModelMapper m = new ModelMapper();
        Usuario usuario = m.map(dto, Usuario.class);

        Usuario usuarioGuardado = uS.insert(usuario);

        UsuarioDtos respuesta = m.map(usuarioGuardado, UsuarioDtos.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
        Usuario usuario = uS.listId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + id);
        }

        uS.delete(id);
        return ResponseEntity.ok("Usuario eliminado.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario usuario = uS.listId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(usuario, UsuarioDtos.class));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody UsuarioDtos dto) {

        ModelMapper m = new ModelMapper();
        Usuario usuario = m.map(dto, Usuario.class);
        try {
            uS.update(usuario);
            return ResponseEntity.ok("Usuario actualizado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/correo")
    public ResponseEntity<?> buscarPorCorreo(@RequestParam("correo") String correo) {
        Usuario usuario = uS.findByCorreo(correo);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el correo: " + correo);
        }

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(usuario, UsuarioDtos.class));
    }

    @GetMapping("/validar-correo")
    public ResponseEntity<?> validarCorreo(@RequestParam("correo") String correo) {
        boolean existe = uS.existsByCorreo(correo);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("correo", correo);
        respuesta.put("duplicado", existe);
        return ResponseEntity.ok(respuesta);
    }
}

package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.PuntosUsuarioDTO;
import pe.edu.upc.civitrackgrupo6.entities.PuntosUsuario;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IPuntosUsuarioService;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IReporteService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/puntosusuario")

public class PuntosUsuarioController {
    @Autowired
    private IPuntosUsuarioService pS;

    @GetMapping
    public List<PuntosUsuarioDTO> listar() {
        return pS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, PuntosUsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody PuntosUsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        PuntosUsuario p = m.map(dto, PuntosUsuario.class);

        // 👇 Asignar manualmente el usuario usando el ID
        Usuario u = new Usuario();
        u.setIdUsuario(dto.getIdUsuario());
        p.setUsuario(u);

        pS.insert(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        PuntosUsuario puntos = pS.listId(id);
        if (puntos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe registro de puntos con el ID: " + id);
        }
        pS.delete(id);
        return ResponseEntity.ok("Puntos de usuario eliminados correctamente.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        PuntosUsuario puntos = pS.listId(id);
        if (puntos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe registro de puntos con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PuntosUsuarioDTO dto = m.map(puntos, PuntosUsuarioDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PuntosUsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        PuntosUsuario p = m.map(dto, PuntosUsuario.class);

        PuntosUsuario existente = pS.listId(p.getIdPuntos());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe registro de puntos con el ID: " + p.getIdPuntos());
        }
        pS.update(p);
        return ResponseEntity.ok("Puntos de usuario modificados correctamente.");
    }
    @Autowired
    private IReporteService rS; // inyecta el servicio de reportes

    @GetMapping("/total/{idUsuario}")
    public ResponseEntity<?> totalPuntosPorUsuario(@PathVariable Long idUsuario) {
        // Contar reportes directamente
        int totalReportes = (int) rS.list().stream()
                .filter(r -> r.getUsuario() != null && r.getUsuario().getIdUsuario() == idUsuario.intValue())
                .count();

        Usuario u = pS.getUsuarioById(idUsuario.intValue());

        LocalDateTime fechaInicio = LocalDateTime.now(); // o la fecha del primer reporte si quieres
        LocalDateTime fechaCaducidad = fechaInicio.plusDays(20);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idUsuario", idUsuario);
        respuesta.put("nombreUsuario", u != null ? u.getNombre() : "---");
        respuesta.put("totalReportes", totalReportes);
        respuesta.put("puntosTotal", totalReportes * 10);
        respuesta.put("nivelActual", calcularNivel(totalReportes * 10));
        respuesta.put("fechaInicio", fechaInicio);
        respuesta.put("fechaCaducidad", fechaCaducidad);

        return ResponseEntity.ok(respuesta);
    }
    // Helper
    private String calcularNivel(int puntos) {
        if (puntos < 50) return "Novato";
        if (puntos < 100) return "Intermedio";
        return "Experto";
    }


    @GetMapping("/ranking")
    public ResponseEntity<?> rankingUsuarios() {
        List<Object[]> datos = pS.rankingUsuarios();

        List<Map<String, Object>> respuesta = datos.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nombreUsuario", obj[0]);
            map.put("totalPuntos", obj[1]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }
}

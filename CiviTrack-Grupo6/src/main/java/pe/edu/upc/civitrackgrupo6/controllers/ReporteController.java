package pe.edu.upc.civitrackgrupo6.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.ReporteDTO;
import pe.edu.upc.civitrackgrupo6.dtos.ReporteRolDTO;
import pe.edu.upc.civitrackgrupo6.entities.Categoria;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;
import pe.edu.upc.civitrackgrupo6.entities.Ubicacion;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IPuntosUsuarioService;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IReporteService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")

public class ReporteController {

    @Autowired
    private IReporteService rS;

    @GetMapping
    public List<ReporteDTO> listar(){
        return rS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,ReporteDTO.class);
        }).collect(Collectors.toList());
    }
    private Reporte convertirDtoAEntidad(ReporteDTO dto) {
        Reporte reporte = new Reporte();

        reporte.setDescripcion(dto.getDescripcion());
        reporte.setEstado(dto.getEstado());
        reporte.setFechaHora(dto.getFechaHora());

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        reporte.setUsuario(usuario);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dto.getIdCategoria());
        reporte.setCategoria(categoria);

        if (dto.getUbicacion() != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(dto.getUbicacion().getLatitud());
            ubicacion.setLongitud(dto.getUbicacion().getLongitud());
            ubicacion.setDistrito(dto.getUbicacion().getDistrito());
            reporte.setUbicacion(ubicacion);
        }

        return reporte;
    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody ReporteDTO dto) {
        Reporte reporte = convertirDtoAEntidad(dto);
        rS.insert(reporte);

        return ResponseEntity.status(HttpStatus.CREATED).body("Reporte creado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Reporte reporte = rS.listId(id);
        if (reporte == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un reporte con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Reporte con ID " + id + " eliminado correctamente.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Reporte reporte = rS.listId(id);
        if (reporte == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un reporte con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ReporteDTO dto = m.map(reporte, ReporteDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        Reporte existente = rS.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un reporte con el ID: " + id);
        }
        // copiar datos del DTO a la entidad existente
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setFechaHora(dto.getFechaHora());
        // etc.
        rS.update(existente);
        return ResponseEntity.ok("Reporte modificado correctamente.");
    }

    @GetMapping("/fecha")
    public ResponseEntity<?> buscarPorFechas(@RequestParam("inicio") String inicio,
                                             @RequestParam("fin") String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);

        // Llamamos al service
        List<ReporteDTO> lista = rS.findReportesPorFecha(fechaInicio, fechaFin).stream()
                .map(r -> new ModelMapper().map(r, ReporteDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/zona")
    public ResponseEntity<?> buscarPorZona(@RequestParam("lat") double lat,
                                           @RequestParam("lng") double lng,
                                           @RequestParam("radio") double radio) {
        List<ReporteDTO> lista = rS.findReportesPorZona(lat, lng, radio).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReporteDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
    @GetMapping("/inconsistentes")
    public ResponseEntity<?> listarInconsistentes() {
        List<ReporteDTO> lista = rS.findReportesInconsistentes().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReporteDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
    @GetMapping("/estadisticas/categorias")
    public ResponseEntity<?> estadisticasPorCategoria() {
        List<Object[]> datos = rS.estadisticasPorCategoria();

        List<Map<String, Object>> respuesta = datos.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("categoria", obj[0]);
            map.put("total", obj[1]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/categorias/top")
    public ResponseEntity<?> topCategorias() {
        List<Object[]> datos = rS.findTopCategorias();

        List<Map<String, Object>> respuesta = datos.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("categoria", obj[0]);
            map.put("cantidad", obj[1]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/por-rol")
    public List<ReporteRolDTO> obtenerReportesPorRol() {
        return rS.cantidadReportesPorRol();
    }

}
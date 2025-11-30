package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.ValidacionDTO;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.entities.Validacion;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IValidacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/validaciones")
public class ValidacionController {

    @Autowired
    private IValidacionService vS;

    private final ModelMapper mapper = new ModelMapper();

    // Listar todas (opcional para admin / debugging)
    @GetMapping
    public List<ValidacionDTO> listar() {
        return vS.list().stream()
                .map(v -> {
                    ValidacionDTO dto = mapper.map(v, ValidacionDTO.class);
                    if (v.getReporte() != null) dto.setReporteId(v.getReporte().getIdReporte());
                    if (v.getUsuario() != null) dto.setUsuarioId(v.getUsuario().getIdUsuario());
                    return dto;
                }).collect(Collectors.toList());
    }

    // Crear validación
    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody ValidacionDTO dto) {

        if (dto.getReporteId() == null || dto.getUsuarioId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Se requiere reporteId y usuarioId para crear la validación.");
        }
        Validacion v = new Validacion();
        v.setComentario(dto.getComentario());
        v.setEstado(dto.getEstado() != null ? dto.getEstado() : "VALIDADO");
        Reporte r = new Reporte();
        r.setIdReporte(dto.getReporteId());
        v.setReporte(r);

        Usuario u = new Usuario();
        u.setIdUsuario(dto.getUsuarioId());
        v.setUsuario(u);

        Validacion creado = vS.insert(v);

        ValidacionDTO resultDto = mapper.map(creado, ValidacionDTO.class);
        if (creado.getReporte() != null) resultDto.setReporteId(creado.getReporte().getIdReporte());
        if (creado.getUsuario() != null) resultDto.setUsuarioId(creado.getUsuario().getIdUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
    }

    // Listar validaciones por reporte
    @GetMapping("/reporte/{idReporte}")
    public ResponseEntity<?> listarPorReporte(@PathVariable("idReporte") Integer idReporte) {
        List<Validacion> lista = vS.findValidacionesPorReporte(idReporte);
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok().body("No se encontraron validaciones para el reporte con ID: " + idReporte);
        }
        List<ValidacionDTO> dtos = lista.stream().map(v -> {
            ValidacionDTO dto = mapper.map(v, ValidacionDTO.class);
            if (v.getReporte() != null) dto.setReporteId(v.getReporte().getIdReporte());
            if (v.getUsuario() != null) dto.setUsuarioId(v.getUsuario().getIdUsuario());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}

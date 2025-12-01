package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.NotificacionDTO;
import pe.edu.upc.civitrackgrupo6.entities.Notificacion;
import pe.edu.upc.civitrackgrupo6.entities.Reporte;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.INotificacionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private INotificacionService nS;

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody NotificacionDTO dto) {

        ModelMapper mapper = new ModelMapper();
        Notificacion n = mapper.map(dto, Notificacion.class);

        n.setUsuario(new Usuario());
        n.getUsuario().setIdUsuario(dto.getIdUsuario());

        n.setReporte(new Reporte());
        n.getReporte().setIdReporte(dto.getIdReporte());

        n.setFechaEnvio(LocalDateTime.now());

        nS.insert(n);
        return ResponseEntity.ok("Notificación registrada exitosamente.");
    }

    @GetMapping
    public List<NotificacionDTO> listar() {
        return nS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            NotificacionDTO dto = m.map(x, NotificacionDTO.class);
            dto.setIdUsuario(x.getUsuario().getIdUsuario());
            dto.setIdReporte(x.getReporte().getIdReporte());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable int id) {
        Notificacion n = nS.listId(id);
        if (n == null)
            return ResponseEntity.status(404).body("No existe notificación con ID " + id);

        ModelMapper m = new ModelMapper();
        NotificacionDTO dto = m.map(n, NotificacionDTO.class);
        dto.setIdUsuario(n.getUsuario().getIdUsuario());
        dto.setIdReporte(n.getReporte().getIdReporte());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<NotificacionDTO> listarPorUsuario(@PathVariable int idUsuario) {
        return nS.findByUsuarioId(idUsuario).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            NotificacionDTO dto = m.map(x, NotificacionDTO.class);
            dto.setIdUsuario(x.getUsuario().getIdUsuario());
            dto.setIdReporte(x.getReporte().getIdReporte());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/fecha")
    public List<NotificacionDTO> listarPorFecha(@RequestParam("value") String fecha) {
        LocalDateTime fechaConsulta = LocalDateTime.parse(fecha);

        return nS.findByFechaEnvio(fechaConsulta).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            NotificacionDTO dto = m.map(x, NotificacionDTO.class);
            dto.setIdUsuario(x.getUsuario().getIdUsuario());
            dto.setIdReporte(x.getReporte().getIdReporte());
            return dto;
        }).collect(Collectors.toList());
    }
}




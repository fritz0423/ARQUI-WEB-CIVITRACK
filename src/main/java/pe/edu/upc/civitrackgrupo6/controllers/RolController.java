package pe.edu.upc.civitrackgrupo6.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.civitrackgrupo6.dtos.RolDTO;
import pe.edu.upc.civitrackgrupo6.entities.Rol;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rS;

    @PostMapping
    public void insert(@RequestBody RolDTO dto) {

        ModelMapper m = new ModelMapper();

        Rol r = m.map(dto, Rol.class);

        rS.insert(r);
    }

    @GetMapping
    public List<RolDTO> list() {


        ModelMapper m = new ModelMapper();

        List<Rol> lista = rS.list();

        return lista.stream()
                .map(x -> m.map(x, RolDTO.class))
                .toList();
    }
    @PutMapping
    public void update(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper(); // ✅ agregar esta línea
        Rol r = m.map(dto, Rol.class);
        rS.update(r);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        rS.delete(id);
    }
}

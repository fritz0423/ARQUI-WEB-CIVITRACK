package pe.edu.upc.civitrackgrupo6.servicesImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.civitrackgrupo6.entities.PuntosUsuario;
import pe.edu.upc.civitrackgrupo6.entities.Usuario;
import pe.edu.upc.civitrackgrupo6.repositories.IPuntosUsuarioRepository;
import pe.edu.upc.civitrackgrupo6.repositories.IUsuarioRepository;
import pe.edu.upc.civitrackgrupo6.servicesInterfaces.IPuntosUsuarioService;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PuntosUsuarioServiceImplement implements IPuntosUsuarioService {
    @Autowired
    private IPuntosUsuarioRepository pR;

    @Autowired
    private IUsuarioRepository uS;
    @Override
    public List<PuntosUsuario> list() {
        return pR.findAll();
    }

    @Override
    public void insert(PuntosUsuario puntosUsuario) {
        pR.save(puntosUsuario);
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }

    @Override
    public PuntosUsuario listId(int id) {
        return pR.findById(id).orElse(null);
    }
    // 🔹 Implementación del nuevo método
    @Override
    public Integer totalReportesPorUsuario(Long idUsuario) {
        return pR.countByUsuarioId(idUsuario);
    }
    @Override
    public void update(PuntosUsuario puntosUsuario) {
        pR.save(puntosUsuario);
    }

    @Override
    public Integer totalPuntosPorUsuario(Long idUsuario) {
        return pR.totalPuntosPorUsuario(idUsuario);
    }

    @Override
    public List<Object[]> rankingUsuarios() {
        return pR.rankingUsuarios();
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {
        return uS.findById(idUsuario)
                .orElse(null); // o lanzar excepción si no existe
    }

    @Override
    public void crearPuntosPorReporte(Long idUsuario) {
        Usuario u = uS.findById(idUsuario.intValue()).orElse(null);
        if (u == null) return;

        PuntosUsuario puntos = new PuntosUsuario();
        puntos.setUsuario(u);
        puntos.setFechaInicio(LocalDateTime.now());

        pR.save(puntos);
    }

}

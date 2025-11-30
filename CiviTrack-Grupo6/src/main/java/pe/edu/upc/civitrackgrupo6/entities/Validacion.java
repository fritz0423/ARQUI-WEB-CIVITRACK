package pe.edu.upc.civitrackgrupo6.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Validaciones")
public class Validacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idValidacion")
    private Integer idValidacion;

    @Column(name = "fechaValidacion", nullable = false)
    private LocalDateTime fechaValidacion = LocalDateTime.now();

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "comentario", length = 500)
    private String comentario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReporte")
    private Reporte reporte;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Validacion() { }

    public Validacion(Integer idValidacion, LocalDateTime fechaValidacion, String estado, String comentario, Reporte reporte, Usuario usuario) {
        this.idValidacion = idValidacion;
        this.fechaValidacion = fechaValidacion;
        this.estado = estado;
        this.comentario = comentario;
        this.reporte = reporte;
        this.usuario = usuario;
    }

    // Getters y setters
    public Integer getIdValidacion() { return idValidacion; }
    public void setIdValidacion(Integer idValidacion) { this.idValidacion = idValidacion; }

    public LocalDateTime getFechaValidacion() { return fechaValidacion; }
    public void setFechaValidacion(LocalDateTime fechaValidacion) { this.fechaValidacion = fechaValidacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Reporte getReporte() { return reporte; }
    public void setReporte(Reporte reporte) { this.reporte = reporte; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}

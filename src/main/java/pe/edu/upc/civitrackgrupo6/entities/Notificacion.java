package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotificacion")
    private int idNotificacion;

    @Column(name = "mensaje", nullable = false, length = 200)
    private String mensaje;

    @Column(name = "fechaEnvio", nullable = false)
    private LocalDateTime fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "idReporte", nullable = false)
    private Reporte reporte;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public Notificacion() {}

    public Notificacion(int idNotificacion, String mensaje, LocalDateTime fechaEnvio, Reporte reporte, Usuario usuario) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.reporte = reporte;
        this.usuario = usuario;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}




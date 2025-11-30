package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Rutas")
public class Rutas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idruta;
    @Column(name="nombreruta", nullable=false, length=50)
    private String nombreruta;
    @Column(name = "latitudorigen", nullable=false, length=50)
    private String latitudorigen;
    @Column(name="latituddestino", nullable=false, length=50)
    private String latituddestino;
    @Column(name="longitudorigen", nullable=false, length=50)
    private String longitudorigen;
    @Column(name = "longituddestino", nullable=false, length=50)
    private String longituddestino;
    @Column(name="fechacreacion", nullable=false)
    private LocalDateTime fechacreacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Rutas() {
    }

    public Rutas(LocalDateTime fechacreacion, int idruta, String latituddestino, String latitudorigen, String longituddestino, String longitudorigen, String nombreruta, Usuario usuario) {
        this.fechacreacion = fechacreacion;
        this.idruta = idruta;
        this.latituddestino = latituddestino;
        this.latitudorigen = latitudorigen;
        this.longituddestino = longituddestino;
        this.longitudorigen = longitudorigen;
        this.nombreruta = nombreruta;
        this.usuario = usuario;
    }

    public LocalDateTime getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(LocalDateTime fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public int getIdruta() {
        return idruta;
    }

    public void setIdruta(int idruta) {
        this.idruta = idruta;
    }

    public String getLatituddestino() {
        return latituddestino;
    }

    public void setLatituddestino(String latituddestino) {
        this.latituddestino = latituddestino;
    }

    public String getLatitudorigen() {
        return latitudorigen;
    }

    public void setLatitudorigen(String latitudorigen) {
        this.latitudorigen = latitudorigen;
    }

    public String getLongituddestino() {
        return longituddestino;
    }

    public void setLongituddestino(String longituddestino) {
        this.longituddestino = longituddestino;
    }

    public String getLongitudorigen() {
        return longitudorigen;
    }

    public void setLongitudorigen(String longitudorigen) {
        this.longitudorigen = longitudorigen;
    }

    public String getNombreruta() {
        return nombreruta;
    }

    public void setNombreruta(String nombreruta) {
        this.nombreruta = nombreruta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

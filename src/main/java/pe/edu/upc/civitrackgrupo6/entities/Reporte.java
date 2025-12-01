package pe.edu.upc.civitrackgrupo6.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reporte")
public class Reporte {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idReporte")
    private int idReporte;

    @Column(name = "descripcion",length =100 ,nullable = false)
    private String descripcion;

    @Column(name = "estado",length =10 ,nullable = false)
    private String estado;

    @Column(name = "fechaHora",nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idUbicacion")
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn (name = "idCategoria")
    private Categoria categoria;



    public Reporte () {
    }

    public Reporte(int idReporte, String descripcion, String estado, LocalDateTime fechaHora, Usuario usuario, Ubicacion ubicacion, Categoria categoria) {
        this.idReporte = idReporte;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}

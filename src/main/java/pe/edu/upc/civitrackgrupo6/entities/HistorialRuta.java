package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistorialRuta")
public class HistorialRuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private int idHistorialRuta;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    // Relación con Ruta
    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Rutas ruta;



    public HistorialRuta() {}

    public HistorialRuta(int idHistorialRuta, LocalDateTime fecha, String descripcion, Rutas ruta) {
        this.idHistorialRuta = idHistorialRuta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.ruta = ruta;
    }

    public int getIdHistorialRuta() {
        return idHistorialRuta;
    }

    public void setIdHistorialRuta(int idHistorialRuta) {
        this.idHistorialRuta = idHistorialRuta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }
}
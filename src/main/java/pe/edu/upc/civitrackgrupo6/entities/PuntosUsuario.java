package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "PuntosUsuario")
public class PuntosUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPuntos")
    private int idPuntos;

    @Column(name = "puntosTotal", nullable = false)
    private int puntosTotal;

    @Column(name = "nivelActual", nullable = false, length = 50)
    private String nivelActual;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fechaCaducidad", nullable = false)
    private LocalDateTime fechaCaducidad;

    // Relación con Usuario
    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    // Constructor vacío
    public PuntosUsuario() {}

    // Constructor con parámetros
    public PuntosUsuario(int idPuntos, int puntosTotal, String nivelActual,
                         LocalDateTime fechaInicio, LocalDateTime fechaCaducidad, Usuario usuario) {
        this.idPuntos = idPuntos;
        this.puntosTotal = puntosTotal;
        this.nivelActual = nivelActual;
        this.fechaInicio = fechaInicio;
        this.fechaCaducidad = fechaCaducidad;
        this.usuario = usuario;
    }

    // Getters y Setters
    public int getIdPuntos() {
        return idPuntos;
    }

    public void setIdPuntos(int idPuntos) {
        this.idPuntos = idPuntos;
    }

    public int getPuntosTotal() {
        return puntosTotal;
    }

    public void setPuntosTotal(int puntosTotal) {
        this.puntosTotal = puntosTotal;
    }

    public String getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(String nivelActual) {
        this.nivelActual = nivelActual;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDateTime fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
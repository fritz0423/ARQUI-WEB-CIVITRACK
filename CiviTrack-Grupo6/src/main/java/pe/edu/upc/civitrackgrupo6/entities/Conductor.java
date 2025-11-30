package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Conductor")
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConductor;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false, unique = true)
    private Usuario usuario;


    private Double promedioCalificacion;


    public Conductor() {
    }

    public Conductor(int idConductor, Usuario usuario, Double promedioCalificacion) {
        this.idConductor = idConductor;
        this.usuario = usuario;
        this.promedioCalificacion = promedioCalificacion;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Double getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(Double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }
}
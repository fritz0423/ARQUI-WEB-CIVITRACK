package pe.edu.upc.civitrackgrupo6.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUbicacion")
    private int idUbicacion;


    @Column(name = "latitud",nullable = false)
    private Double latitud;

    @Column(name = "longitud",nullable = false)
    private Double longitud;

    @Column(name = "distrito",nullable = false)
    private String distrito;


    public Ubicacion() {
    }

    public Ubicacion(int idUbicacion, Double latitud, Double longitud, String distrito) {
        this.idUbicacion = idUbicacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distrito = distrito;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

}

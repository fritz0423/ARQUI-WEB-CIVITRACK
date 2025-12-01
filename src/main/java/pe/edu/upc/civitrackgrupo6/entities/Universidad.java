package pe.edu.upc.civitrackgrupo6.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Universidad")

public class
Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUniversidad")
    private int idUniversidad;

    @Column(name = "nombreUniversidad", nullable = false)
    private String nombreUniversidad;


    @Column(name = "dominio", nullable = false, length = 100)
    private String dominio;

    public Universidad() {

    }
    public Universidad(int idUniversidad, String nombreUniversidad, String dominio) {
        this.idUniversidad = idUniversidad;
        this.nombreUniversidad = nombreUniversidad;
        this.dominio = dominio;
    }

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
}

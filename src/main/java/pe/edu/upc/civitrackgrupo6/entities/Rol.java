package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String permisos;

    public Long getId() {
        return idRol;
    }

    public void setId(Long id) {
        this.idRol = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
}


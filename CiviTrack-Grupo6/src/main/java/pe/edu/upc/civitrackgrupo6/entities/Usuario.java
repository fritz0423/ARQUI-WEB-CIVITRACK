package pe.edu.upc.civitrackgrupo6.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Usuario")
public class Usuario {
//es esteeeeee
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="correo", nullable = false, length = 30, unique = true)
    private String correo;

    @Column(name="password", nullable = false, length = 200)
    private String password;

    @Column(name="fechaRegistro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Estudiante estudiante;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name="idRol", nullable=false)
    private Rol rol;

    public Usuario() {
    }
/*
    public Usuario(String correo, LocalDateTime fecharegistro, int idUsuario, String nombre, String password, Rol rol) {
        this.correo = correo;
        this.fechaRegistro = fecharegistro;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }*/

    public Usuario(int idUsuario, String nombre, String correo, String password, LocalDateTime fechaRegistro, Estudiante estudiante, Conductor conductor, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.estudiante = estudiante;
        this.conductor = conductor;
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

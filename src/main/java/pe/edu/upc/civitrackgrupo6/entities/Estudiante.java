package pe.edu.upc.civitrackgrupo6.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstudiante")
    private int idEstudiante;


    @Column(name = "codigoEstudiante", nullable = false)
    private String codigoEstudiante;

    @Column(name = "carreraEstudiante", nullable = false)
    private String carreraEstudiante;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "anioIngresoEstudiante", nullable = false)
    private String anioIngresoEstudiante;

    @ManyToOne
    @JoinColumn(name = "idUniversidad", nullable = false)
    private Universidad universidad;

    public Estudiante() {

    }

    public Estudiante(int idEstudiante, String codigoEstudiante, String carreraEstudiante, Usuario usuario, String anioIngresoEstudiante, Universidad universidad) {
        this.idEstudiante = idEstudiante;
        this.codigoEstudiante = codigoEstudiante;
        this.carreraEstudiante = carreraEstudiante;
        this.usuario = usuario;
        this.anioIngresoEstudiante = anioIngresoEstudiante;
        this.universidad = universidad;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getCarreraEstudiante() {
        return carreraEstudiante;
    }

    public void setCarreraEstudiante(String carreraEstudiante) {
        this.carreraEstudiante = carreraEstudiante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAnioIngresoEstudiante() {
        return anioIngresoEstudiante;
    }

    public void setAnioIngresoEstudiante(String anioIngresoEstudiante) {
        this.anioIngresoEstudiante = anioIngresoEstudiante;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }
}
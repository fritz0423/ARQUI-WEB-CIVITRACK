package pe.edu.upc.civitrackgrupo6.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehiculo")
    private int idVehiculo;

    @Column(name = "placaVehiculo", nullable = false)
    private String placaVehiculo;

    @Column(name = "marcaVehiculo", nullable = false)
    private String marcaVehiculo;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConductor", nullable = false)
    private Conductor conductor;

    public Vehiculo (){

    }

    public Vehiculo(int idVehiculo, String placaVehiculo, String marcaVehiculo, String estado, Conductor conductor) {
        this.idVehiculo = idVehiculo;
        this.placaVehiculo = placaVehiculo;
        this.marcaVehiculo = marcaVehiculo;
        this.estado = estado;
        this.conductor = conductor;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
}

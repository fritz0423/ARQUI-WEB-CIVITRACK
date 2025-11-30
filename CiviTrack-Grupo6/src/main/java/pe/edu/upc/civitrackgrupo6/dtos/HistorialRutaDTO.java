package pe.edu.upc.civitrackgrupo6.dtos;

import java.time.LocalDateTime;

public class HistorialRutaDTO {
    private int idHistorialRuta;
    private LocalDateTime fecha;
    private String descripcion;
    private int idruta;

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

    public int getIdruta() {
        return idruta;
    }

    public void setIdruta(int idruta) {
        this.idruta = idruta;
    }
}

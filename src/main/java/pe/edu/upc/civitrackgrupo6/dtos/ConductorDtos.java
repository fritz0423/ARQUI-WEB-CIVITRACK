package pe.edu.upc.civitrackgrupo6.dtos;

public class ConductorDtos {

    private int idConductor;
    private int idUsuario; // solo el ID, no la entidad
    private Double promedioCalificacion;

    public Double getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(Double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
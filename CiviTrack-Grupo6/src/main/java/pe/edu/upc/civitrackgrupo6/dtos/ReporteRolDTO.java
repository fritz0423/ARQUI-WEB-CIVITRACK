package pe.edu.upc.civitrackgrupo6.dtos;

public class ReporteRolDTO {
    private String rol;
    private Long cantidad;

    public ReporteRolDTO() {}

    public ReporteRolDTO(String rol, Long cantidad) {
        this.rol = rol;
        this.cantidad = cantidad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}


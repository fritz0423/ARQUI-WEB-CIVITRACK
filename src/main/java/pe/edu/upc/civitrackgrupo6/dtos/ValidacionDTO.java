package pe.edu.upc.civitrackgrupo6.dtos;

import java.time.LocalDateTime;

public class ValidacionDTO {

    private Integer idValidacion;
    private Integer reporteId;
    private Integer usuarioId;
    private String estado;
    private String comentario;
    private LocalDateTime fechaValidacion;

    public ValidacionDTO() {}

    // Getters y setters
    public Integer getIdValidacion() { return idValidacion; }
    public void setIdValidacion(Integer idValidacion) { this.idValidacion = idValidacion; }

    public Integer getReporteId() { return reporteId; }
    public void setReporteId(Integer reporteId) { this.reporteId = reporteId; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getFechaValidacion() { return fechaValidacion; }
    public void setFechaValidacion(LocalDateTime fechaValidacion) { this.fechaValidacion = fechaValidacion; }
}

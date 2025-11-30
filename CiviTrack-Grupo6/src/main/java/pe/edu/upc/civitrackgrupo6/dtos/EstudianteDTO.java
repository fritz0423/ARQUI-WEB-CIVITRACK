package pe.edu.upc.civitrackgrupo6.dtos;


public class EstudianteDTO {

    private int idEstudiante;

    private String codigoEstudiante;

    private String carreraEstudiante;

    private int idUsuario;

    private String anioIngresoEstudiante;

    private int idUniversidad;

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAnioIngresoEstudiante() {
        return anioIngresoEstudiante;
    }

    public void setAnioIngresoEstudiante(String anioIngresoEstudiante) {
        this.anioIngresoEstudiante = anioIngresoEstudiante;
    }
}

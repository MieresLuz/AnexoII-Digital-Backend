package LedLabs.AnexoIIDigital.dtos;

import java.util.List;

public class InscripcionDTO {
    private String dni;
    private String numeroTramite;
    private List<String> materias;

    // Constructor vacío
    public InscripcionDTO() {
    }

    // Constructor completo (opcional)
    public InscripcionDTO(String dni, String numeroTramite, List<String> materias) {
        this.dni = dni;
        this.numeroTramite = numeroTramite;
        this.materias = materias;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(String numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }
}


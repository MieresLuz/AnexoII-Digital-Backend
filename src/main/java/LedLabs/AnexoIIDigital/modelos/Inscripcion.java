package LedLabs.AnexoIIDigital.modelos;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroTramite;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ElementCollection
    private List<String> codigosMaterias;

    public Inscripcion() {
    }

    public Inscripcion(String numeroTramite, Estudiante estudiante, List<String> codigosMaterias) {
        this.numeroTramite = numeroTramite;
        this.estudiante = estudiante;
        this.codigosMaterias = codigosMaterias;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(String numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<String> getCodigosMaterias() {
        return codigosMaterias;
    }

    public void setCodigosMaterias(List<String> codigosMaterias) {
        this.codigosMaterias = codigosMaterias;
    }

    public void addEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        estudiante.getInscripciones().add(this);
    }

}

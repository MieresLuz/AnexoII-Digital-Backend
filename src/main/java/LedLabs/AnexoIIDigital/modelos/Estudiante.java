package LedLabs.AnexoIIDigital.modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String contraseña;
    private String departamento;
    private String instituto;
    private String carrera;
    private String legajo;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String dni, String email, String contraseña, String departamento, String instituto, String carrera, String legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.contraseña = contraseña;
        this.departamento = departamento;
        this.instituto = instituto;
        this.carrera = carrera;
        this.legajo = legajo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void addInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
        inscripcion.setEstudiante(this);
    }

}

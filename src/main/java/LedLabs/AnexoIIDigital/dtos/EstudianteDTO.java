package LedLabs.AnexoIIDigital.dtos;

import LedLabs.AnexoIIDigital.modelos.Estudiante;

public class EstudianteDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String contraseña;
    private String departamento;
    private String instituto;
    private String carrera;
    private String legajo;

    public EstudianteDTO(Estudiante estudiante){
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.dni = estudiante.getDni();
        this.email = estudiante.getEmail();
        this.contraseña = estudiante.getContraseña();
        this.departamento = estudiante.getDepartamento();
        this.instituto = estudiante.getInstituto();
        this.carrera = estudiante.getCarrera();
        this.legajo = estudiante.getLegajo();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getInstituto() {
        return instituto;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getLegajo() {
        return legajo;
    }
}

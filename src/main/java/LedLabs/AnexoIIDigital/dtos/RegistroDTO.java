package LedLabs.AnexoIIDigital.dtos;

public record RegistroDTO(
        String nombre,
        String apellido,
        String dni,
        String email,
        String contraseña,
        String tipoUsuario,

        // Campos adicionales para estudiante
        String instituto,
        String carrera,
        Integer anioIngreso,
        String legajo,

        // Campos adicionales para docente
        String cargo,
        String departamento,
        String telefono,
        String direccion
) {}
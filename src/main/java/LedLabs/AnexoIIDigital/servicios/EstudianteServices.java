package LedLabs.AnexoIIDigital.servicios;

import LedLabs.AnexoIIDigital.modelos.Estudiante;

public interface EstudianteServices {
    Estudiante findByEmail(String email);
}

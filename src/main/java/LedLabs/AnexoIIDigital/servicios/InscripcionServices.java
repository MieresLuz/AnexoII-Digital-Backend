package LedLabs.AnexoIIDigital.servicios;

import LedLabs.AnexoIIDigital.modelos.Inscripcion;

import java.util.Optional;

public interface InscripcionServices {
    Optional<Inscripcion> obtenerPorDni(String dni);
    Inscripcion guardar(Inscripcion inscripcion);
}

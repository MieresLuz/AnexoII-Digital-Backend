package LedLabs.AnexoIIDigital.repositorios;

import LedLabs.AnexoIIDigital.modelos.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    Optional<Inscripcion> findByEstudiante_Dni(String dni);
}

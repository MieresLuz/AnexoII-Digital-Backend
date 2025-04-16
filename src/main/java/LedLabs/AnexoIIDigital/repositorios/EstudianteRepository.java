package LedLabs.AnexoIIDigital.repositorios;

import LedLabs.AnexoIIDigital.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByEmail(String email);
}

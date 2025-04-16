package LedLabs.AnexoIIDigital.servicios.implementacion;

import LedLabs.AnexoIIDigital.modelos.Estudiante;
import LedLabs.AnexoIIDigital.repositorios.EstudianteRepository;
import LedLabs.AnexoIIDigital.servicios.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServicesImpl implements EstudianteServices {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Override
    public Estudiante findByEmail(String email) {
        return estudianteRepository.findByEmail(email); // No se utiliza orElse(null) aquí
    }
}

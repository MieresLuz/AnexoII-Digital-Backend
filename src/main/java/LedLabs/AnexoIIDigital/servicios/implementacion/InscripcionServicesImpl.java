package LedLabs.AnexoIIDigital.servicios.implementacion;

import LedLabs.AnexoIIDigital.modelos.Inscripcion;
import LedLabs.AnexoIIDigital.repositorios.InscripcionRepository;
import LedLabs.AnexoIIDigital.servicios.InscripcionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscripcionServicesImpl implements InscripcionServices {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public Optional<Inscripcion> obtenerPorDni(String dni) {
        return inscripcionRepository.findByEstudiante_Dni(dni);
    }

    @Override
    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }
}


package LedLabs.AnexoIIDigital.controladores;

import LedLabs.AnexoIIDigital.dtos.InscripcionDTO;
import LedLabs.AnexoIIDigital.modelos.Estudiante;
import LedLabs.AnexoIIDigital.modelos.Inscripcion;
import LedLabs.AnexoIIDigital.repositorios.EstudianteRepository;
import LedLabs.AnexoIIDigital.servicios.InscripcionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    @Autowired
    private InscripcionServices inscripcionServices;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/existe/{dni}")
    public Map<String, Boolean> verificarInscripcion(@PathVariable String dni) {
        boolean existe = inscripcionServices.obtenerPorDni(dni).isPresent();
        return Collections.singletonMap("existe", existe);
    }

    @PostMapping("/registrar")
    public Map<String, Object> registrarInscripcion(@RequestBody InscripcionDTO dto) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findByDni(dto.getDni());
        if (estudianteOpt.isEmpty()) {
            return Map.of("success", false, "message", "Estudiante no encontrado");
        }

        Estudiante estudiante = estudianteOpt.get();

        if (inscripcionServices.obtenerPorDni(dto.getDni()).isPresent()) {
            return Map.of("success", false, "message", "Ya estás inscripto");
        }

        Inscripcion inscripcion = new Inscripcion(dto.getNumeroTramite(), estudiante, dto.getMaterias());
        inscripcionServices.guardar(inscripcion);

        return Map.of("success", true, "message", "Inscripción registrada correctamente");
    }
}

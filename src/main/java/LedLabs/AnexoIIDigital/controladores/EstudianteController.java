package LedLabs.AnexoIIDigital.controladores;

import LedLabs.AnexoIIDigital.servicios.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*") // o ajustá según tu política CORS
public class EstudianteController {

    @Autowired
    private EstudianteServices estudianteServices;

}

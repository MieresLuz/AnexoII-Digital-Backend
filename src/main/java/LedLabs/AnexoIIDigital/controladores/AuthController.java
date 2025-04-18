package LedLabs.AnexoIIDigital.controladores;

import LedLabs.AnexoIIDigital.dtos.EstudianteDTO;
import LedLabs.AnexoIIDigital.dtos.LoginDTO;
import LedLabs.AnexoIIDigital.dtos.RegistroDTO;
import LedLabs.AnexoIIDigital.modelos.Estudiante;
import LedLabs.AnexoIIDigital.repositorios.EstudianteRepository;
import LedLabs.AnexoIIDigital.servicios.AuthServices;
import LedLabs.AnexoIIDigital.serviciosSeguridad.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = authServices.login(loginDTO);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroDTO registroDTO) {
        try {
            // El tipoUsuario ahora está dentro del cuerpo de la solicitud
            String tipoUsuario = registroDTO.tipoUsuario();
            Object usuario = authServices.registroUsuario(registroDTO, tipoUsuario);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        Estudiante estudiante = estudianteRepository.findByEmail(authentication.getName());
        if (estudiante != null) {
            return ResponseEntity.ok(new EstudianteDTO(estudiante));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
    }
}

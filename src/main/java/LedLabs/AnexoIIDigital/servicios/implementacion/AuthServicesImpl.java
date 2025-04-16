package LedLabs.AnexoIIDigital.servicios.implementacion;

import LedLabs.AnexoIIDigital.dtos.EstudianteDTO;
import LedLabs.AnexoIIDigital.dtos.LoginDTO;
import LedLabs.AnexoIIDigital.dtos.RegistroDTO;
import LedLabs.AnexoIIDigital.modelos.Estudiante;
import LedLabs.AnexoIIDigital.repositorios.EstudianteRepository;
import LedLabs.AnexoIIDigital.servicios.AuthServices;
import LedLabs.AnexoIIDigital.servicios.EstudianteServices;
import LedLabs.AnexoIIDigital.serviciosSeguridad.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
public class AuthServicesImpl implements AuthServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private EstudianteServices estudianteServices;

    @Autowired
    private EstudianteRepository estudianteRepository;

    private final Pattern namePattern = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúñÑ][A-Za-zÁÉÍÓÚáéíóúñÑ ]*$");
    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$");
    private final Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\p{Punct}])[A-Za-z\\d\\p{Punct}]{8,}$");

    @Override
    public String login(LoginDTO loginDTO) {
        // Validar el email
        validarEmail(loginDTO.email());
        // Buscar al usuario en la base de datos (estudiante o docente)
        Estudiante existeEstudiante = estudianteServices.findByEmail(loginDTO.email());
        if (existeEstudiante == null) {
            throw new IllegalArgumentException("El email no está registrado");
        }
        // Aquí se debe crear un UsernamePasswordAuthenticationToken
        // con el email y la contraseña en texto plano.
        // El AuthenticationManager será quien se encargue de verificar si las credenciales son correctas
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.contraseña())
            );
        } catch (Exception e) {
            // Si la autenticación falla, lanzar una excepción con mensaje adecuado
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        // Si la autenticación fue exitosa, generar el JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());
        return jwtUtilService.generateToken(userDetails);
    }



    @Override
    public Object registroUsuario(RegistroDTO registroDTO, String tipoUsuario) {
        // Validar campos
        validarNombre(registroDTO.nombre(), "Nombre");
        validarNombre(registroDTO.apellido(), "Apellido");
        validarEmail(registroDTO.email());
        validarContraseña(registroDTO.contraseña());

        // Verificar si ya existe
        Estudiante existeEstudiante = estudianteServices.findByEmail(registroDTO.email());

        if (existeEstudiante != null) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        // Crear Estudiante o Docente según el tipo
        if ("estudiante".equalsIgnoreCase(tipoUsuario)) {
            Estudiante estudiante = new Estudiante(
                    registroDTO.nombre(),
                    registroDTO.apellido(),
                    registroDTO.dni(),
                    registroDTO.email(),
                    passwordEncoder.encode(registroDTO.contraseña()),
                    registroDTO.departamento(),
                    registroDTO.instituto(),
                    registroDTO.carrera(),
                    registroDTO.legajo()
            );
            estudianteRepository.save(estudiante);
            return new EstudianteDTO(estudiante);
        } else {
            throw new IllegalArgumentException("Tipo de usuario inválido");
        }
    }

    @Override
    public EstudianteDTO currentEstudiante(String email) {
        Estudiante estudiante = estudianteServices.findByEmail(email);
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante con el email " + email + " no se encuentra");
        }
        return new EstudianteDTO(estudiante);
    }

    // Métodos de validación
    private void validarNombre(String nombre, String campoNombre) {
        if (nombre == null || nombre.trim().isBlank()) {
            throw new IllegalArgumentException(campoNombre + " no puede estar vacío ni contener espacios.");
        }
        if (!namePattern.matcher(nombre).matches()) {
            throw new IllegalArgumentException(campoNombre + " no es válido, solo se permiten letras.");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.trim().isBlank()) {
            throw new IllegalArgumentException("El campo email no puede estar vacío ni contener espacios.");
        }
        if (!emailPattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Formato de correo electrónico no válido, debe contener @ y .com");
        }
    }

    private void validarContraseña(String contraseña) {
        if (!passwordPattern.matcher(contraseña).matches()) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres, contener una letra mayúscula, una letra minúscula, un número y un carácter especial.");
        }
    }
}


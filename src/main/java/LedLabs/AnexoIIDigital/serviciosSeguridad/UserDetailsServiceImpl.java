package LedLabs.AnexoIIDigital.serviciosSeguridad;

import LedLabs.AnexoIIDigital.modelos.Estudiante;
import LedLabs.AnexoIIDigital.servicios.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EstudianteServices estudianteServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar Estudiante
        Estudiante estudiante = estudianteServices.findByEmail(username);
        if (estudiante != null) {
            // Obtener roles del estudiante (por ejemplo, si tienes un campo roles en la entidad)
            List<SimpleGrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
            return User
                    .withUsername(username)
                    .password(estudiante.getContraseña()) // Asegúrate de que esta contraseña está cifrada
                    .authorities(roles)
                    .build();
        }

        // Si no se encuentra el usuario
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}

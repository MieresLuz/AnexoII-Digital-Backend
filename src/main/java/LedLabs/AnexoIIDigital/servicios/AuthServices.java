package LedLabs.AnexoIIDigital.servicios;

import LedLabs.AnexoIIDigital.dtos.EstudianteDTO;
import LedLabs.AnexoIIDigital.dtos.LoginDTO;
import LedLabs.AnexoIIDigital.dtos.RegistroDTO;

public interface AuthServices {
    String login(LoginDTO loginDTO);
    Object registroUsuario(RegistroDTO registroDTO, String tipoUsuario);  // Unificado
    EstudianteDTO currentEstudiante(String email);
}
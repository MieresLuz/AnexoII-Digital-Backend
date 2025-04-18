package LedLabs.AnexoIIDigital.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {

    @GetMapping(value = {
            "/", "/registro", "/anexoII", "/login"
    })
    public String forwardReactRoutes() {
        return "forward:/index.html";
    }
}

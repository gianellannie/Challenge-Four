package forohub.Challenge_Four.controlador;

import forohub.Challenge_Four.dominio.usuario.DatosAutenticacion;
import forohub.Challenge_Four.dominio.usuario.DatosRespuestaAutenticacion;
import forohub.Challenge_Four.dominio.usuario.Usuario;
import forohub.Challenge_Four.infra.seguridad.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionControlador {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(
        @RequestBody
        @Valid
        DatosAutenticacion datos
    ){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                datos.nombre(),
                datos.contrasena()
        );
        Authentication usuarioAutenticado = authenticationManager
                .authenticate(authenticationToken);
        String jwTtoken = tokenService
                .generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosRespuestaAutenticacion(jwTtoken));
    }
}

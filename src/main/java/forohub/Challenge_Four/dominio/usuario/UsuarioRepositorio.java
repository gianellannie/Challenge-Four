package forohub.Challenge_Four.dominio.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    UserDetails findByNombre(String nombreUsuario);
}

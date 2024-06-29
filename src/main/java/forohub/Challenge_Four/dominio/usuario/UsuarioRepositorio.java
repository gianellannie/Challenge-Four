package forohub.Challenge_Four.dominio.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    UserDetails findByNombre(String nombreUsuario);
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombreUsuario")
    Optional<Usuario> buscarNombre(String nombreUsuario);
    Optional<Usuario> findByEmail(String email);
}

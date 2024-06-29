package forohub.Challenge_Four.dominio.usuario;

import forohub.Challenge_Four.dominio.topico.validacion.ValidadorDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ValidadorDeIntegridad validadorDeIntegridad;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatosRespuestaUsuario registrar(DatosRegistrarUsuario datos) {
        if(usuarioRepositorio.buscarNombre(datos.nombre()).isPresent()){
            throw new ValidationException("No se permite nombres duplicados.");
        }
        if(usuarioRepositorio.findByEmail(datos.email()).isPresent()){
            throw new ValidationException("No se permite emails duplicados.");
        }
        String contrasenaCodificado = passwordEncoder.encode(datos.contrasena());
        Usuario usuario = new Usuario(datos.nombre(), datos.email(), contrasenaCodificado);
        usuarioRepositorio.save(usuario);
        return new DatosRespuestaUsuario(usuario);
    }

    public Page<DatosRespuestaUsuario> mostrarUsuarios(Pageable paginacion) {
        return usuarioRepositorio.findAll(paginacion).map(DatosRespuestaUsuario::new);
    }

    public DatosRespuestaUsuario mostrarUsuario(Long id) {
        validadorDeIntegridad.validarIdUsuario(id);
        Usuario usuario = usuarioRepositorio.findById(id).get();
        return new DatosRespuestaUsuario(usuario);
    }

    public DatosRespuestaUsuario actualizar(Long id, DatosActualizarUsuario datos) {
        validadorDeIntegridad.validarIdUsuario(id);
        Usuario usuario = usuarioRepositorio.getReferenceById(id);
        String contrasenaCodificado = passwordEncoder.encode(datos.contrasena());
        usuario.actualizar(datos.nombre(),datos.email(),contrasenaCodificado);
        return new DatosRespuestaUsuario(usuario);
    }

    public void eliminar(Long id) {
        validadorDeIntegridad.validarIdUsuario(id);
        usuarioRepositorio.deleteById(id);
    }
}

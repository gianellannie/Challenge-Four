package forohub.Challenge_Four.controlador;

import forohub.Challenge_Four.dominio.usuario.DatosActualizarUsuario;
import forohub.Challenge_Four.dominio.usuario.DatosRegistrarUsuario;
import forohub.Challenge_Four.dominio.usuario.DatosRespuestaUsuario;
import forohub.Challenge_Four.dominio.usuario.UsuarioServicio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrar(
            @RequestBody
            @Valid
            DatosRegistrarUsuario datos,
            UriComponentsBuilder uriComponentsBuilder
    ){
        DatosRespuestaUsuario respuesta = usuarioServicio.registrar(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(respuesta.id())
                .toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> mostrarUsuarios(
            @PageableDefault(size = 5)
            Pageable paginacion
    ){
        Page<DatosRespuestaUsuario> usuarios = usuarioServicio.mostrarUsuarios(paginacion);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> mostrarUsuario(
            @PathVariable
            Long id
    ){
        DatosRespuestaUsuario usuario = usuarioServicio.mostrarUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizar(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            DatosActualizarUsuario datos
    ){
        DatosRespuestaUsuario topico = usuarioServicio.actualizar(id,datos);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(
            @PathVariable
            Long id
    ){
        usuarioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

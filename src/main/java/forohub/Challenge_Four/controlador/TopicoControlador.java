package forohub.Challenge_Four.controlador;

import forohub.Challenge_Four.dominio.topico.*;
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
@RequestMapping("/topicos")
public class TopicoControlador {
    @Autowired
    private TopicoRepositorio topicoRepositorio;
    @Autowired
    private TopicoServicio topicoServicio;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(
            @RequestBody
            @Valid
            DatosRegistrarTopico datos,
            UriComponentsBuilder uriComponentsBuilder
    ){
        DatosRespuestaTopico respuesta = topicoServicio.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(respuesta.id())
                .toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> mostrarTopicos(
            @PageableDefault(size = 10)
            Pageable paginacion,
            @RequestParam(required = false)
            String nombreCurso,
            @RequestParam(required = false)
            Integer anoEspecifico
    ){
        Page<DatosRespuestaTopico> topicos = topicoServicio
                .mostrarTopicos(paginacion,nombreCurso,anoEspecifico);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> mostrarTopico(
            @PathVariable
            Long id
    ){
        DatosRespuestaTopico topico = topicoServicio.mostrarTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            DatosActualizarTopico datos
    ){
        DatosRespuestaTopico topico = topicoServicio.actualizar(id,datos);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(
            @PathVariable
            Long id
    ){
        topicoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

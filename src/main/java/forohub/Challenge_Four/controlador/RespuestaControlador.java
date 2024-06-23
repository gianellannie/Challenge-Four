package forohub.Challenge_Four.controlador;

import forohub.Challenge_Four.dominio.respuesta.DatosActualizarRespuesta;
import forohub.Challenge_Four.dominio.respuesta.DatosRegistrarRespuesta;
import forohub.Challenge_Four.dominio.respuesta.DatosRespuesta;
import forohub.Challenge_Four.dominio.respuesta.RespuestaServicio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaControlador {
    @Autowired
    private RespuestaServicio respuestaServicio;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuesta> registrar(
            @RequestBody
            @Valid
            DatosRegistrarRespuesta datos,
            UriComponentsBuilder uriComponentsBuilder
    ){
        DatosRespuesta respuesta = respuestaServicio.registrar(datos);
        URI url = uriComponentsBuilder.path("/respuestas/{id}")
                .buildAndExpand(respuesta.id())
                .toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuesta> actualizar(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            DatosActualizarRespuesta datos
    ){
        DatosRespuesta respuesta = respuestaServicio.actualizar(id,datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(
            @PathVariable
            Long id
    ){
        respuestaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

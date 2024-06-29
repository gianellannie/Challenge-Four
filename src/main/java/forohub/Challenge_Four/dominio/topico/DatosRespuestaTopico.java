package forohub.Challenge_Four.dominio.topico;

import forohub.Challenge_Four.dominio.respuesta.DatosRespuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String nombreUsuario,
        String nombreCurso,
        List<DatosRespuesta> respuestas
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre(),
                topico.getRespuestas().stream()
                        .map(DatosRespuesta::new)
                        .toList()
        );
    }
}

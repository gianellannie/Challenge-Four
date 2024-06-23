package forohub.Challenge_Four.dominio.topico;

import forohub.Challenge_Four.dominio.respuesta.DatosRespuesta;

import java.time.LocalDateTime;

public record DatosRespuestaRegistrar(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String nombreUsuario,
        String nombreCurso
) {
    public DatosRespuestaRegistrar(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}

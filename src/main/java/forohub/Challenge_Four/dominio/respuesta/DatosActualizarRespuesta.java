package forohub.Challenge_Four.dominio.respuesta;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarRespuesta(
        @NotBlank
        String mensaje
) {
}

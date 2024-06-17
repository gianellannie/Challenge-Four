package forohub.Challenge_Four.dominio.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank
        String nombre,
        @NotBlank
        String contrasena
) {
}

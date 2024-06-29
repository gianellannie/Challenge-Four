package forohub.Challenge_Four.dominio.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String email
) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}

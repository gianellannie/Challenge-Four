package forohub.Challenge_Four.dominio.respuesta;

import forohub.Challenge_Four.dominio.topico.Topico;
import forohub.Challenge_Four.dominio.topico.TopicoRepositorio;
import forohub.Challenge_Four.dominio.topico.validacion.ValidadorDeIntegridad;
import forohub.Challenge_Four.dominio.usuario.Usuario;
import forohub.Challenge_Four.dominio.usuario.UsuarioRepositorio;
import forohub.Challenge_Four.infra.error.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespuestaServicio {
    @Autowired
    private RespuestaRepositorio respuestaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TopicoRepositorio topicoRepositorio;
    @Autowired
    private ValidadorDeIntegridad validadorDeIntegridad;

    public DatosRespuesta registrar(
            DatosRegistrarRespuesta datos
    ) {
        validadorDeIntegridad.validarIdTopico(datos.idTopico());
        validadorDeIntegridad.validarIdUsuario(datos.idUsuario());
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Topico topico = topicoRepositorio.findById(datos.idTopico()).get();
        Usuario usuario = usuarioRepositorio.findById(datos.idUsuario()).get();
        Respuesta respuesta = new Respuesta(datos.mensaje(), fechaCreacion, topico, usuario);
        respuestaRepositorio.save(respuesta);
        return new DatosRespuesta(respuesta);
    }

    public DatosRespuesta actualizar(Long id, DatosActualizarRespuesta datos) {
        if (!respuestaRepositorio.existsById(id)){
            throw new ValidacionIntegridad("El id de la respuesta no fue encontrado.");
        }
        LocalDateTime fechaActualizacion = LocalDateTime.now();
        Respuesta respuesta = respuestaRepositorio.getReferenceById(id);
        respuesta.actualizar(datos.mensaje(),fechaActualizacion);
        return new DatosRespuesta(respuesta);
    }

    public void eliminar(Long id) {
        if (!respuestaRepositorio.existsById(id)){
            throw new ValidacionIntegridad("El id de la respuesta no fue encontrado.");
        }
        respuestaRepositorio.deleteById(id);
    }
}

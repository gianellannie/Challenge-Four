package forohub.Challenge_Four.dominio.topico;

import forohub.Challenge_Four.dominio.curso.Curso;
import forohub.Challenge_Four.dominio.curso.CursoRepositorio;
import forohub.Challenge_Four.dominio.topico.validacion.ValidadorDeIntegridad;
import forohub.Challenge_Four.dominio.topico.validacion.ValidadorDeTopicos;
import forohub.Challenge_Four.dominio.usuario.Usuario;
import forohub.Challenge_Four.dominio.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoServicio {
    @Autowired
    private CursoRepositorio cursoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TopicoRepositorio topicoRepositorio;
    @Autowired
    private List<ValidadorDeTopicos> validadores;
    @Autowired
    private ValidadorDeIntegridad validadorDeIntegridad;

    public DatosRespuestaTopico registrar(
            DatosRegistrarTopico datos
    ){
        validadorDeIntegridad.validarIdUsuario(datos.idUsuario());
        validadorDeIntegridad.validarNombreCurso(datos.nombreCurso());
        validadores.forEach(v->v.validar(datos.titulo(), datos.mensaje()));
        Usuario usuario = usuarioRepositorio.findById(datos.idUsuario()).get();
        Curso curso = cursoRepositorio.findByNombreIgnoreCase(datos.nombreCurso()).get();
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Topico topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                fechaCreacion,
                usuario,
                curso
        );
        topicoRepositorio.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    public Page<DatosRespuestaTopico> mostrarTopicos(
            Pageable paginacion,
            String nombreCurso,
            Integer anoEspecifico
    ){
        if(nombreCurso!=null && anoEspecifico!=null){
            validadorDeIntegridad.validarNombreCurso(nombreCurso);
            Long idCurso = cursoRepositorio.findByNombreIgnoreCase(nombreCurso).get().getId();
            return topicoRepositorio.criterioBusqueda(paginacion,idCurso,anoEspecifico)
                    .map(DatosRespuestaTopico::new);
        }
        return topicoRepositorio.mostrar(paginacion)
                .map(DatosRespuestaTopico::new);
    }

    public DatosRespuestaTopico mostrarTopico(Long id) {
        validadorDeIntegridad.validarIdTopico(id);
        return new DatosRespuestaTopico(topicoRepositorio.findById(id).get());
    }

    public DatosRespuestaTopico actualizar(
            Long id,
            DatosActualizarTopico datos
    ) {
        validadorDeIntegridad.validarIdTopico(id);
        validadorDeIntegridad.validarNombreCurso(datos.nombreCurso());
        validadores.forEach(v->v.validar(datos.titulo(), datos.mensaje()));
        Curso curso = cursoRepositorio.findByNombreIgnoreCase(datos.nombreCurso()).get();
        LocalDateTime fechaActualizacion = LocalDateTime.now();
        Topico topico = topicoRepositorio.getReferenceById(id);
        topico.actualizar(datos,curso,fechaActualizacion);
        return new DatosRespuestaTopico(topico);
    }

    public void eliminar(Long id) {
        validadorDeIntegridad.validarIdTopico(id);
        topicoRepositorio.deleteById(id);
    }
}

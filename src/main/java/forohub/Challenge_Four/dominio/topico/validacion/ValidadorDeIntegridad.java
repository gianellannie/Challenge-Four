package forohub.Challenge_Four.dominio.topico.validacion;

import forohub.Challenge_Four.dominio.curso.CursoRepositorio;
import forohub.Challenge_Four.dominio.topico.TopicoRepositorio;
import forohub.Challenge_Four.dominio.usuario.UsuarioRepositorio;
import forohub.Challenge_Four.infra.error.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorDeIntegridad {
    @Autowired
    private CursoRepositorio cursoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TopicoRepositorio topicoRepositorio;

    public void validarIdUsuario(Long idUsuario){
        if(!usuarioRepositorio.existsById(idUsuario)){
            throw new ValidacionIntegridad("El id del usuario no fue encontrado.");
        }
    }
    public void validarNombreCurso(String name){
        if (cursoRepositorio.findByNombreIgnoreCase(name).isEmpty()){
            throw new ValidacionIntegridad("El nombre del curso no fue encontrado.");
        }
    }
    public void validarIdTopico(Long idTopico){
        if (!topicoRepositorio.existsById(idTopico)){
            throw new ValidacionIntegridad("El id del tópico no fue encontrado.");
        }
    }
}

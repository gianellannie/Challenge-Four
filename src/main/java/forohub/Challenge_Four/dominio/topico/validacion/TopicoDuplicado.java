package forohub.Challenge_Four.dominio.topico.validacion;

import forohub.Challenge_Four.dominio.topico.TopicoRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoDuplicado implements ValidadorDeTopicos{
    @Autowired
    private TopicoRepositorio topicoRepositorio;

    public void validar(String titulo,String mensaje){
        boolean topicoBD = topicoRepositorio
                .findByTituloIgnoreCaseAndMensajeIgnoreCase(titulo,mensaje)
                .isPresent();
        if(topicoBD){
            throw new ValidationException("No se permite tópicos duplicados.");
        }
    }
}
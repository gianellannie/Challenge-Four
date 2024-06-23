package forohub.Challenge_Four.dominio.respuesta;

import forohub.Challenge_Four.dominio.topico.Topico;
import forohub.Challenge_Four.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    private Topico topico;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Respuesta(String mensaje, LocalDateTime fechaCreacion, Topico topico, Usuario usuario){
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.topico = topico;
        this.usuario = usuario;
    }

    public void actualizar(String mensaje, LocalDateTime fechaActualizacion) {
        this.mensaje = mensaje;
        this.fechaCreacion = fechaActualizacion;
    }
}

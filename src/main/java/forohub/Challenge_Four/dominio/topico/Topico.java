package forohub.Challenge_Four.dominio.topico;

import forohub.Challenge_Four.dominio.curso.Curso;
import forohub.Challenge_Four.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    public Topico(
            String titulo,
            String mensaje,
            LocalDateTime fechaCreacion,
            Usuario usuario,
            Curso curso
    ) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.estado = true;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizar(
            DatosActualizarTopico datos,
            Curso curso,
            LocalDateTime fechaActualizacion
    ) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = fechaActualizacion;
        this.curso = curso;
    }
}

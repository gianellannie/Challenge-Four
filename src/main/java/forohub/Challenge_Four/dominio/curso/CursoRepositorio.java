package forohub.Challenge_Four.dominio.curso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepositorio extends JpaRepository<Curso,Long> {
    Optional<Curso> findByNombreIgnoreCase(String nombreCurso);
}

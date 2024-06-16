package forohub.Challenge_Four.dominio.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepositorio extends JpaRepository<Topico,Long> {
    Optional<Topico> findByTituloIgnoreCaseAndMensajeIgnoreCase(String titulo, String mensaje);
    @Query("""
            SELECT t FROM Topico t
            WHERE t.curso.id = :idCurso
            AND
            YEAR(t.fechaCreacion) = :year
            ORDER BY t.fechaCreacion ASC
            """)
    Page<Topico> criterioBusqueda(Pageable paginacion, Long idCurso, Integer year);
    @Query("""
            SELECT t FROM Topico t
            ORDER BY t.fechaCreacion ASC
            """)
    Page<Topico> mostrar(Pageable paginacion);
}

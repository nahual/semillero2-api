package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface GraduatedRepository extends JpaRepository<Graduated, Long> {

    Collection<Graduated> findByNode(Node node);

	Collection<Graduated> findByDate(LocalDate date);
}

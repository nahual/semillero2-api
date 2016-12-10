package ar.edu.undav.semillero.domain.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;

public interface GraduatedRepository extends JpaRepository<Graduated, Long> {

	Collection<Graduated> findByName(String name);

	Collection<Graduated> findByNode(Node node);

	Collection<Graduated> findByDate(Date date);

	Graduated findById(Long id);

}

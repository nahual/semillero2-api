package ar.edu.undav.semillero.domain.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.semillero.domain.entity.Node;

public interface NodeRepository extends JpaRepository<Node, Long> {

	Collection<Node> findByName(String name);

	Node findById(Long id);

}

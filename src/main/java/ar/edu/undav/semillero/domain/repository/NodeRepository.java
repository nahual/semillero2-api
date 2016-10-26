package ar.edu.undav.semillero.domain.repository;


import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;


public interface NodeRepository extends JpaRepository<Node, Long> {
	
    Collection<Node> findByName(String name);
    Collection<Node> findById(Long id);
	
}


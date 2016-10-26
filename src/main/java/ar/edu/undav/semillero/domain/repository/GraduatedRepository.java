package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface GraduatedRepository extends JpaRepository<Graduated, Long> {

    Collection<Graduated> findByName(String name);
    Collection<Graduated> findById(Long id);

}


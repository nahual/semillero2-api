package ar.edu.undav.semillero.domain.repository;


import ar.edu.undav.semillero.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterviewRepository extends JpaRepository<Interview, Long> {
	
	
}


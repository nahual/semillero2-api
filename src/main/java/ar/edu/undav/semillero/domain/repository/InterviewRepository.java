package ar.edu.undav.semillero.domain.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
	Interview findById(Long id);

	Collection<Interview> findByDate(Date date);

	Collection<Interview> findByGraduated(Graduated gId);
}

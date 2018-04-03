package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    Collection<Interview> findByDate(LocalDate date);

    Collection<Interview> findByGraduated(Graduated graduated);

    Collection<Interview> findAllByOrderByIdDesc();
}

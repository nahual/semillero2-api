package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    Collection<Interview> findByDate(Date date);

    Collection<Interview> findByGraduated(Graduated graduated);

    Collection<Interview> findAllByOrderByIdDesc();
}

package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Student;
import ar.edu.undav.semillero.domain.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByNode(Node node);

    Collection<Student> findByDate(LocalDate date);

    @Modifying
    @Query("update ar.edu.undav.semillero.domain.entity.Student g set g.deleted = true where g.id = :id")
    int softDeleteById(@Param("id") long id);
}

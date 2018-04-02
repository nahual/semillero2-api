package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Collection<Company> findByName(String name);
}

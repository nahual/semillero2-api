package ar.edu.undav.semillero.domain.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.undav.semillero.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Collection<Company> findByName(String name);
}

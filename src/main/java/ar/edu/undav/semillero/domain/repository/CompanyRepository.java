package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Company;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Collection<Company> findByName(String name);
	Collection<Company> findById(Long id);
}


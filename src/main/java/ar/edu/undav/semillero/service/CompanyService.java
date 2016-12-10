package ar.edu.undav.semillero.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public void save(Company company) {
		companyRepository.save(company);
	}

	public Collection<Company> findAll() {
		return companyRepository.findAll();
	}

	public Company findById(Long id) {
		return companyRepository.findOne(id);
	}

	public Collection<Company> findByName(String name) {
		return companyRepository.findByName(name);
	}

}

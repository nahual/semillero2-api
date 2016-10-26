package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


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
    
    public Collection<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
    
    public Collection<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }
 
}

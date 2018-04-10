package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.repository.CompanyRepository;
import ar.edu.undav.semillero.request.CreateCompanyRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Company save(CreateCompanyRequest request) {
        return companyRepository.save(new Company(request.getName(), request.getContact()));
    }

    @Transactional(readOnly = true)
    public Collection<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }
}

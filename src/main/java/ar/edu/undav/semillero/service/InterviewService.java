package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.domain.repository.CompanyRepository;
import ar.edu.undav.semillero.domain.repository.GraduatedRepository;
import ar.edu.undav.semillero.domain.repository.InterviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final GraduatedRepository graduatedRepository;
    private final CompanyRepository companyRepository;

    public InterviewService(InterviewRepository interviewRepository, GraduatedRepository graduatedRepository, CompanyRepository companyRepository) {
        this.interviewRepository = interviewRepository;
        this.graduatedRepository = graduatedRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Interview save(long graduatedId, long companyId) {
        Graduated graduated = graduatedRepository.getOne(graduatedId);
        Company company = companyRepository.getOne(companyId);
        return interviewRepository.save(new Interview(graduated, company, new Date(), "Sin comentarios"));
    }

    @Transactional(readOnly = true)
    public Collection<Interview> findAll() {
        return interviewRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Interview> findById(Long id) {
        return interviewRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<Interview> findByDate(Date date) {
        return interviewRepository.findByDate(date);
    }

    @Transactional(readOnly = true)
    public Collection<Interview> findByGraduated(Graduated gId) {
        return interviewRepository.findByGraduated(gId);
    }

    @Transactional(readOnly = true)
    public Collection<Interview> findAllByOrderByIdDesc() {
        return interviewRepository.findAllByOrderByIdDesc();
    }
}

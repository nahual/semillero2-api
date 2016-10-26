package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.domain.repository.CompanyRepository;
import ar.edu.undav.semillero.domain.repository.InterviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    public void save(Interview interview) {
    	interviewRepository.save(interview);
    }

    public Collection<Interview> findAll() {
        return interviewRepository.findAll();
    }
    
 
}

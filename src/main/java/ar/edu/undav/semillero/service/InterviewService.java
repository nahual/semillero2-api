package ar.edu.undav.semillero.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.domain.repository.InterviewRepository;

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

	public Interview findById(Long id) {
		return interviewRepository.findById(id);
	}

	public Collection<Interview> findByDate(Date date) {
		return interviewRepository.findByDate(date);
	}

	public Collection<Interview> findByGraduated(Graduated gId) {
		return interviewRepository.findByGraduated(gId);
	}
	
	public Collection<Interview> findAllByOrderByIdDesc(){
		return interviewRepository.findAllByOrderByIdDesc();
	}

}

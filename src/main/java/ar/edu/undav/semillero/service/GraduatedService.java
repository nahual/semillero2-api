package ar.edu.undav.semillero.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.domain.repository.GraduatedRepository;

@Service
public class GraduatedService {

	@Autowired
	private GraduatedRepository graduatedRepository;

	public void save(Graduated graduated) {
		graduatedRepository.save(graduated);
	}

	public Collection<Graduated> findAll() {
		return graduatedRepository.findAll();
	}

	public Graduated findById(Long id) {
		return graduatedRepository.findById(id);
	}

	public Collection<Graduated> findByNode(Node node) {
		return graduatedRepository.findByNode(node);
	}

	public Collection<Graduated> findByDate(Date date) {
		return graduatedRepository.findByDate(date);
	}

}

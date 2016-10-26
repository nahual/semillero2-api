package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.repository.GraduatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


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
    
    public Collection<Graduated> findById(Long id) {
        return graduatedRepository.findById(id);
    }
    
}

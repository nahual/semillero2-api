package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.repository.GraduatedRepository;
import ar.edu.undav.semillero.domain.repository.NodeRepository;
import ar.edu.undav.semillero.request.CreateGraduatedRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class GraduatedService {

    private final GraduatedRepository graduatedRepository;
    private final NodeRepository nodeRepository;

    public GraduatedService(GraduatedRepository graduatedRepository, NodeRepository nodeRepository) {
        this.graduatedRepository = graduatedRepository;
        this.nodeRepository = nodeRepository;
    }

    @Transactional
    public Graduated save(CreateGraduatedRequest request) {
        return nodeRepository.findById(request.getNode())
                .map(node -> graduatedRepository.save(new Graduated(request.getName(), node)))
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public boolean deleteById(long id) {
        return graduatedRepository.softDeleteById(id) == 1;
    }

    @Transactional(readOnly = true)
    public Collection<Graduated> findAll() {
        return graduatedRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Graduated> findById(Long id) {
        return graduatedRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<Graduated> findByNode(long nodeId) {
        return nodeRepository.findById(nodeId)
                .map(graduatedRepository::findByNode)
                .orElseGet(Collections::emptyList);
    }

    @Transactional(readOnly = true)
    public Collection<Graduated> findByDate(LocalDate when) {
        return graduatedRepository.findByDate(when);
    }
}

package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.domain.repository.NodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Transactional
    public Node save(Node node) {
        return nodeRepository.save(node);
    }

    @Transactional(readOnly = true)
    public Collection<Node> findAll() {
        return nodeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Node> findById(Long id) {
        return nodeRepository.findById(id);
    }
}

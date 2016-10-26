package ar.edu.undav.semillero.service;


import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.domain.repository.NodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    public void save(Node node) {
    	nodeRepository.save(node);
    }

    public Collection<Node> findAll() {
        return nodeRepository.findAll();
    }
    
    public Collection<Node> findById(Long id) {
        return nodeRepository.findById(id);
    }
    
    public Collection<Node> findByName(String name) {
        return nodeRepository.findByName(name);
    }
}

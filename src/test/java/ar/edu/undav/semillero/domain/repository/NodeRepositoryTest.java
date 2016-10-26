package ar.edu.undav.semillero.domain.repository;


import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.domain.entity.Node;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NodeRepositoryTest {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private GraduatedRepository graduatedRepository;
    
    
    @Test
    public void testSave() {
        Graduated graduated = new Graduated("Daniel");
        Node node = new Node("Bariloche","Calle Verdadera 123");
        graduated.setNode(node);
        node.addGraduated(graduated);
        nodeRepository.save(node);
        graduatedRepository.save(graduated);
    }


}
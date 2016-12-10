package ar.edu.undav.semillero.domain.repository;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application.test.properties")
public class NodeRepositoryTest {

	@Autowired
	private NodeRepository nodeRepository;

	@Autowired
	private GraduatedRepository graduatedRepository;

	@Test
	public void testSave() {
		Node node = new Node("Bariloche", "Calle Verdadera 123");
		Graduated graduated = new Graduated("Daniel", node, new Date());
		node.addGraduated(graduated);
		nodeRepository.save(node);
		graduatedRepository.save(graduated);
	}

}
package ar.edu.undav.semillero.domain.repository;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.service.CompanyService;
import ar.edu.undav.semillero.service.NodeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application.test.properties")
public class GraduatedRepositoryTest {

	@Autowired
	private GraduatedRepository graduatedRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private NodeService nodeService;

	@Test
	public void testSave() {
		Node node = nodeService.findById((long) 1);
		Company company = companyService.findById((long) 1);
		Graduated graduated = new Graduated("Daniel", node, new Date());

		graduated.addInterview(new Interview(graduated, company, new Date(), "no comments"));

		graduatedRepository.save(graduated);
	}

}
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
import ar.edu.undav.semillero.service.CompanyService;
import ar.edu.undav.semillero.service.GraduatedService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application.test.properties")
public class InterviewRepositoryTest {

	@Autowired
	private InterviewRepository interviewRepository;
	@Autowired
	private GraduatedService graduatedService;
	@Autowired
	private CompanyService companyService;

	@Test
	public void testSave() {
		Graduated graduated = graduatedService.findById((long) 1);
		Company company = companyService.findById((long) 1);
		Interview entrevista = new Interview(graduated, company, new Date(), "Hay que contratarlo al toque");
		interviewRepository.save(entrevista);
	}

}
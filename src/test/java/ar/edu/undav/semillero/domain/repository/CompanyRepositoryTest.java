package ar.edu.undav.semillero.domain.repository;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ar.edu.undav.semillero.domain.entity.Company;

/**
 * Juan Lagostena on 12/10/16 jlagostena@bitsense.com.ar .
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application.test.properties")
public class CompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;

	@Test
	public void testSave() {
		Company company = new Company("compañia", "Pepe");
		companyRepository.save(company);

	}

	@Test
	public void testFindAll() {
		Collection<Company> companies = companyRepository.findAll();
		for (Company company : companies) {
			System.out.println(company);
		}
	}

}
package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Interview;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application.test.properties")
public class InterviewRepositoryTest {

    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private GraduatedRepository graduatedRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testSave() {
        graduatedRepository.findById(1001L)
                .flatMap(graduated -> companyRepository.findById(1001L).map(company -> Pair.of(graduated, company)))
                .map(pair -> {
                    Interview entrevista = new Interview(pair.getFirst(), pair.getSecond(), new Date(), "Hay que contratarlo al toque");
                    return interviewRepository.save(entrevista);
                })
                .orElseThrow(RuntimeException::new);
    }
}

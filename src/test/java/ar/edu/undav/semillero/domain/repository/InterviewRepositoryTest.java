package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Interview;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collection;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class InterviewRepositoryTest {

    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testSave() {
        studentRepository.findById(1001L)
                .flatMap(student -> companyRepository.findById(1001L).map(company -> new Interview(student, company, "Hay que contratarlo al toque")))
                .map(interviewRepository::save)
                .orElseThrow(RuntimeException::new);
    }

    @Test
    public void testFindByDate() {
        Collection<Interview> interviews = interviewRepository.findByDate(LocalDate.of(2016, 11, 2));
        Assertions.assertThat(interviews).hasSize(2);
    }
}

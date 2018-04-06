package ar.edu.undav.semillero.domain.repository;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class GraduatedRepositoryTest {

    @Autowired
    private GraduatedRepository graduatedRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void testSave() {
        nodeRepository.findById(1001L)
                .flatMap(node -> companyRepository.findById(1001L).map(company -> Pair.of(node, company)))
                .map(pair -> {
                    Graduated graduated = new Graduated("Daniel", pair.getFirst());
                    graduated.addInterview(new Interview(graduated, pair.getSecond(), "no comments"));
                    return graduatedRepository.save(graduated);
                })
                .orElseThrow(RuntimeException::new);
    }
}

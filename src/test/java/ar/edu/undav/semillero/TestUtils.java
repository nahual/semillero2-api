package ar.edu.undav.semillero;

import ar.edu.undav.semillero.dto.CompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

/**
 * @author Alejandro Gomez
 */
public abstract class TestUtils {

    private TestUtils() {
    }

    public static CompanyDTO createCompanyDTO() {
        return new CompanyDTO(1L, "Pivotal", "info@pivotal.com", 2, 4);
    }

    public static Page<CompanyDTO> createCompanyDTOPage() {
        return new PageImpl<>(Collections.singletonList(createCompanyDTO()), createPageRequest(), 1);
    }

    public static Page<CompanyDTO> createCompanyDTOEmptyPage() {
        return new PageImpl<>(Collections.emptyList(), createPageRequest(), 0);
    }

    public static PageRequest createPageRequest() {
        return PageRequest.of(0, 20);
    }
}

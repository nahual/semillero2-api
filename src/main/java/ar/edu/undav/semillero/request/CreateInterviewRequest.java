package ar.edu.undav.semillero.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Alejandro Gomez
 */
public class CreateInterviewRequest extends BaseRequest {

    @NotNull
    private final Long graduated;
    @NotNull
    private final Long company;

    public CreateInterviewRequest(@JsonProperty("graduated") Long graduated, @JsonProperty("company") Long company) {
        this.graduated = graduated;
        this.company = company;
    }

    public Long getGraduated() {
        return graduated;
    }

    public Long getCompany() {
        return company;
    }
}

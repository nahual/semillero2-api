package ar.edu.undav.semillero.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @author Alejandro Gomez
 */
public class CreateCompanyRequest extends BaseRequest {

    @NotEmpty
    private final String name;
    @NotEmpty
    private final String contact;

    public CreateCompanyRequest(@JsonProperty("name") String name, @JsonProperty("contact") String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

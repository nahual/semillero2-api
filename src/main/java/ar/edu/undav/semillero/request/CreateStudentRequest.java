package ar.edu.undav.semillero.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Alejandro Gomez
 */
public class CreateStudentRequest extends BaseRequest {

    @NotEmpty
    private final String name;
    @NotNull
    private final Long node;
    private final String email;
    private final String resumeUrl;

    public CreateStudentRequest(@JsonProperty("name") String name, @JsonProperty("node") Long node, @JsonProperty("email") String email, @JsonProperty("resumeUrl") String resumeUrl) {
        this.name = name;
        this.node = node;
        this.email = email;
        this.resumeUrl = resumeUrl;
    }

    public String getName() {
        return name;
    }

    public Long getNode() {
        return node;
    }

    public String getEmail() {
        return email;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }
}

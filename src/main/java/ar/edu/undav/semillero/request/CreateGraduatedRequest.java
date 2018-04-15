package ar.edu.undav.semillero.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Alejandro Gomez
 */
public class CreateGraduatedRequest extends BaseRequest {

    @NotEmpty
    private final String name;
    @NotNull
    private final Long node;
    private final String contact;
    private final String resumeUrl;

    public CreateGraduatedRequest(@JsonProperty("name") String name, @JsonProperty("node") Long node,@JsonProperty("contact") String contact, @JsonProperty("resumeUrl") String resumeUrl) {
        this.name = name;
        this.node = node;
        this.contact = contact;
        this.resumeUrl = resumeUrl;
    }

    public String getName() {
        return name;
    }

    public Long getNode() {
        return node;
    }

    public String getContact() {
        return contact;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }
}

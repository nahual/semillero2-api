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

    public CreateGraduatedRequest(@JsonProperty("name") String name, @JsonProperty("node") Long node) {
        this.name = name;
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public Long getNode() {
        return node;
    }
}

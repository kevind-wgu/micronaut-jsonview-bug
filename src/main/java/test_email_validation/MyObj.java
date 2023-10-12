package test_email_validation;

import com.fasterxml.jackson.annotation.JsonView;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Introspected
public class MyObj {
    private String id;

    private String field;

    @JsonView(Views.Internal.class)
    private String hiddenField;
}

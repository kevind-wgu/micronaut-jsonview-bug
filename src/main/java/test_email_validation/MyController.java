package test_email_validation;

import com.fasterxml.jackson.annotation.JsonView;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class MyController {
    @Get("/myobj/{id}")
    @JsonView(Views.Public.class)
    public MyObj getIt(String id) {
        if ("err".equals(id)) {
            throw new MyObjException("My Exception Message");
        }
        else {
            return new MyObj(id, "seeable", "hidden");
        }
    }
}

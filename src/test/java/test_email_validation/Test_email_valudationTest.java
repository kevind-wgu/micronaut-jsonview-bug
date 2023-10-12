package test_email_validation;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@WireMockTest()
class Test_email_valudationTest {
    @RegisterExtension
    static WireMockExtension downloadWm = WireMockExtension.newInstance().build();

    @Test
    void test_sucess(RequestSpecification spec) {
        ValidatableResponse res = spec.given()
                .contentType(ContentType.JSON)
                .get("/myobj/abc")
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);
        res.body("id", response -> equalTo("abc"));
        res.body("hiddenField", response -> equalTo(null));
    }

    @Test
    void test_notFound(RequestSpecification spec) {
        spec.given()
                .contentType(ContentType.JSON)
                .get("/myobj/err")
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(404);

    }

}

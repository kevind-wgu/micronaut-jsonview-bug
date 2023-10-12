package test_email_validation;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@MicronautTest
@WireMockTest()
class Test_email_valudationTest {
    @RegisterExtension
    static WireMockExtension downloadWm = WireMockExtension.newInstance().build();

    @Test
    void test_sucess(RequestSpecification spec) {
        spec.given()
                .contentType(ContentType.JSON)
                .get("/myobj/123")
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);

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

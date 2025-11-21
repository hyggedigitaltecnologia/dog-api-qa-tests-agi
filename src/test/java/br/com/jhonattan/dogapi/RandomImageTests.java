package br.com.jhonattan.dogapi;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API")
@Feature("Imagem randômica")
public class RandomImageTests extends BaseApiTest {

    @RepeatedTest(3)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Deve retornar uma imagem randômica com sucesso")
    @Description("Valida status 200, status='success' e URL de imagem .jpg.")
    void shouldReturnRandomImage() {
        logEndpoint("/breeds/image/random");

        given()
                .when()
                .get("/breeds/image/random")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", allOf(
                        notNullValue(),
                        startsWith("https://"),
                        endsWith(".jpg")
                ));
    }
}
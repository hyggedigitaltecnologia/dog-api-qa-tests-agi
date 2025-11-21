package br.com.jhonattan.dogapi;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API")
@Feature("Imagens por raça")
public class BreedImagesTests extends BaseApiTest {

    @ParameterizedTest(name = "Raça válida: {0}")
    @ValueSource(strings = { "hound", "pug", "bulldog" })
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve retornar lista de imagens para raças válidas")
    @Description("Valida status 200, status='success' e lista não vazia de URLs.")
    void shouldReturnImagesForValidBreeds(String breed) {
        logEndpoint("/breed/" + breed + "/images");

        given()
                .when()
                .get("/breed/{breed}/images", breed)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .body("message.size()", greaterThan(0))
                .body("message[0]", startsWith("https://"));
    }

    @ParameterizedTest(name = "Raça inválida: {0}")
    @ValueSource(strings = { "invalidBreed", "doge", "1234" })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar erro para raças inválidas")
    @Description("Valida resposta de erro quando a raça não existe.")
    void shouldReturnErrorForInvalidBreeds(String breed) {
        logEndpoint("/breed/" + breed + "/images");

        given()
                .when()
                .get("/breed/{breed}/images", breed)
                .then()
                .statusCode(404)
                .body("status", equalTo("error"))
                .body("code", equalTo(404))
                .body("message", containsString("Breed not found"));
    }
}
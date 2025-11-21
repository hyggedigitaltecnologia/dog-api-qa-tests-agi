package br.com.jhonattan.dogapi;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API")
@Feature("Listagem de Raças")
public class BreedsListTests extends BaseApiTest {

    @Test
    @DisplayName("Deve retornar todas as raças com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida status HTTP, campo 'status' e estrutura básica da resposta.")
    void shouldReturnAllBreedsSuccessfully() {
        logEndpoint("/breeds/list/all");

        given()
                .when()
                .get("/breeds/list/all")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .body("message.size()", greaterThan(0))
                .body("message.hound", notNullValue()); // exemplo de raça
    }
}
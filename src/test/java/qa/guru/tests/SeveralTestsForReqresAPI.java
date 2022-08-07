package qa.guru.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class SeveralTestsForReqresAPI extends BaseTest {
    int userId;

    @Test
    @DisplayName("Проверка создания нового юзера методом /api/users и параметров ответа созданного юзера")
    void creatingUser() {
        given().
                contentType(JSON)
                .body(dataForTheTest.jsonBodyToCreate.toString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(dataForTheTest.userName)
                        , "job", equalTo(dataForTheTest.userJob)
                        , "id", notNullValue()
                        , "createdAt", greaterThan(dataForTheTest.timeBeforeStartTest));
    }

    @Test
    @DisplayName("Создаёт юзера, затем обновляет информацию по созданному юзеру методом /api/users/{id юзера}")
    void updatingUserInfo() {
        userId = Integer.parseInt(given().
                contentType(JSON)
                .body(dataForTheTest.jsonBodyToCreate.toString())
                .when()
                .post("/api/users")
                .then()
                .extract()
                .path("id"));

        given().
                contentType(JSON)
                .body(dataForTheTest.jsonBodyToUpdate.toString())
                .when()
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(dataForTheTest.userNameToUpdate)
                        , "job", equalTo(dataForTheTest.userJobToUpdate)
                        , "updatedAt", greaterThan(dataForTheTest.timeBeforeStartTest));
    }

    @Test
    @DisplayName("Создаём юзера, затем удаленяем информацию по юзеру /api/users/{id юзера}")
    void deletingUser() {
        userId = Integer.parseInt(given().
                contentType(JSON)
                .body(dataForTheTest.jsonBodyToCreate.toString())
                .when()
                .post("/api/users")
                .then()
                .extract()
                .path("id"));

        given().
                contentType(JSON)
                .when()
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Получает информацию по одном пользователю /api/users/{id юзера}")
    void gettingUser() {
        given().
                contentType(JSON)
                .when()
                .get("/api/users/" + dataForTheTest.randomUserId)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(dataForTheTest.randomUserId)
                        , "data.email", containsString("@reqres.in")
                        , "data.first_name", notNullValue()
                        , "data.avatar", containsString(dataForTheTest.randomUserId + "-image.jpg"));
    }

    @Test
    @DisplayName("Неудачная попытка логирования в методе api/login")
    void loggingUser() {
        given().
                contentType(JSON)
                .body(dataForTheTest.jsonBodyUnsuccessfullLogin.toString())
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}

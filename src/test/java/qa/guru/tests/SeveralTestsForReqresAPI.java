package qa.guru.tests;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class SeveralTestsForReqresAPI extends BaseTest {
    JSONObject jsonObj;
    int userId;

    @Test
    @DisplayName("Проверка создания нового юзера методом /api/users и параметров ответа созданного юзера")
    void creatingUser() {
        jsonObj = new JSONObject()
                .put("name", dataForTheTest.userName)
                .put("job", dataForTheTest.userJob);

        userId = Integer.parseInt(given().
                contentType(JSON)
                .body(jsonObj.toString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(dataForTheTest.userName)
                        , "job", equalTo(dataForTheTest.userJob)
                        , "id", notNullValue()
                        , "createdAt", greaterThan(dataForTheTest.timeBeforeStartTest))
                .extract().path("id"));
    }

    @Test
    @DisplayName("Обновление информации по созданному юзеру методом /api/users/{id юзера}}")
    void updatingUserInfo() {
        jsonObj = new JSONObject()
                .put("name", dataForTheTest.userNameToUpdate)
                .put("job", dataForTheTest.userJobToUpdate);

        given().
                contentType(JSON)
                .body(jsonObj.toString())
                .when()
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(dataForTheTest.userNameToUpdate)
                        , "job", equalTo(dataForTheTest.userJobToUpdate)
                        , "updatedAt", greaterThan(dataForTheTest.timeBeforeStartTest));
    }

    @Test
    @DisplayName("Удаление информации по пользователю /api/users/{id юзера}")
    void deletingUser() {
        given().
                contentType(JSON)
                .log().all()
                .when()
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204);
    }
}

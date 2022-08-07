package qa.guru.tests;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class SeveralTestsForReqresAPI extends BaseTest {
    JSONObject jsonObj;

    @Test
    @DisplayName("Проверка создания нового юзера методом /api/users и параметров ответа созданного юзера")
    void creatingUser() {
        jsonObj = new JSONObject()
                .put("name", dataForTheTest.userName)
                .put("job", dataForTheTest.userJob);

        given().
                contentType(JSON)
                .body(jsonObj.toString())
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
    @DisplayName("Обновление информации по созданному юзеру методом /api/users/2")
    void updatingUserInfo() {
        jsonObj = new JSONObject()
                .put("name", dataForTheTest.userNameToUpdate)
                .put("job", dataForTheTest.userJobToUpdate);

        given().
                contentType(JSON)
                .body(jsonObj.toString())
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo(dataForTheTest.userNameToUpdate)
                        , "job", equalTo(dataForTheTest.userJobToUpdate)
                        , "updatedAt", greaterThan(dataForTheTest.timeBeforeStartTest));
    }
}

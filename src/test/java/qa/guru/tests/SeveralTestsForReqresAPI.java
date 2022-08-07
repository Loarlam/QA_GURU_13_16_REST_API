package qa.guru.tests;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class SeveralTestsForReqresAPI extends BaseTest {
    @Test
    @DisplayName("Проверка создания нового юзера методом /api/users")
    void createUser() {
        JSONObject jsonObj = new JSONObject().put("name", dataForTheTest.userName).put("job", dataForTheTest.userJob);

        given().
                contentType(JSON).
                body(jsonObj.toString()).
                when().
                post("/api/users").
                then().
                statusCode(201)
                .body("name", equalTo(dataForTheTest.userName)
                        , "job", equalTo(dataForTheTest.userJob)
                        , "id", notNullValue()
                        , "createdAt", notNullValue());
    }
}

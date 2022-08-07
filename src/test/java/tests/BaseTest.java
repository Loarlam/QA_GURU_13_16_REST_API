package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void settingURI() {
        RestAssured.baseURI = "https://reqres.in";
    }
}

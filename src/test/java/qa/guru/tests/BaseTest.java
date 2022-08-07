package qa.guru.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    DataForTheTest dataForTheTest = new DataForTheTest();

    @BeforeAll
    static void settingURI() {
        RestAssured.baseURI = "https://reqres.in";
    }
}

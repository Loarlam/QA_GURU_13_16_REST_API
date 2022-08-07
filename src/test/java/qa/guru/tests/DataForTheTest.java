package qa.guru.tests;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Locale;
import java.util.Random;

public class DataForTheTest {
    Faker fakerData = new Faker(Locale.FRANCE);
    Random random = new Random();

    int randomUserId = random.nextInt(10) + 1;

    String userName = fakerData.funnyName().name(),
            userNameToUpdate = fakerData.funnyName().name(),
            userJob = fakerData.job().position(),
            userJobToUpdate = fakerData.job().position(),
            timeBeforeStartTest = Instant.now().toString(),
            userUnsuccessfullLogin = fakerData.internet().safeEmailAddress();

    JSONObject jsonBodyToCreate = new JSONObject()
                .put("name", userName)
                .put("job", userJob),
               jsonBodyToUpdate = new JSONObject()
                .put("name", userNameToUpdate)
                .put("job", userJobToUpdate),
               jsonBodyUnsuccessfullLogin = new JSONObject()
                .put("email", userUnsuccessfullLogin);
}
package qa.guru.tests;

import com.github.javafaker.Faker;

import java.time.Month;
import java.util.Locale;
import java.util.Random;

public class DataForTheTest {
    Faker fakerData = new Faker(Locale.FRANCE);
    Random random = new Random();

    int userDayOfBirth = random.nextInt(28) + 1,
            userYearOfBirth = random.nextInt(101) + 1900;

    String userName = fakerData.funnyName().name(),
            userSurname = fakerData.name().lastName(),
            userJob = fakerData.job().position(),
            userEmail = fakerData.internet().safeEmailAddress(),
            userPhoneNumber = String.valueOf(random.nextInt(1000000000) + 9000000000L),
            userMonthOfBirth = Month.values()[random.nextInt(Month.values().length)].name(),
            userAddress = fakerData.address().fullAddress();
}
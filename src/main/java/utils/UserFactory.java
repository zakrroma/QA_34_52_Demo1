package utils;

import dto.UserData;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static UserData positiveUser() {
        return UserData.builder()
                .userName(faker.internet().emailAddress())
                .password("Qwerty123!")
                .build();
    }
}

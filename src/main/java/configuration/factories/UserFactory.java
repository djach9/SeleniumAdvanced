package configuration.factories;

import configuration.User;

import java.util.List;

import static configuration.ConfigurationRetriever.getUserData;
import static helpers.Helper.getFaker;
import static helpers.Helper.getRandom;

public class UserFactory {

    public static User getAlreadyRegisteredUser() {
        return new User.UserBuilder()
                .socialTitle(getUserData().getSocialTitle())
                .firstName(getUserData().getFirstName())
                .lastName(getUserData().getLastName())
                .email(getUserData().getEmail())
                .password(getUserData().getPassword())
                .birthdate(getUserData().getBirthdate())
                .build();
    }

    public static User getRandomUser() {
        String firstName = getFaker().name().firstName();
        String lastName = getFaker().name().lastName();
        return new User.UserBuilder()
                .socialTitle(List.of("Mr.", "Mrs.").get(getRandom().nextInt(2)))
                .firstName(firstName)
                .lastName(lastName)
                .email(firstName + "." + lastName + "@test.com")
                .password(getFaker().phoneNumber().cellPhone())
                .build();
    }
}
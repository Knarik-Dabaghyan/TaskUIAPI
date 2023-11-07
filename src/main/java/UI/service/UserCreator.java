package UI.service;

import UI.model.User;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User getCredentialsFromProperty() {
        return User.builder()
                .userEmail(TestDataReader.getTestData(TESTDATA_USER_NAME))
                .userPassword(TestDataReader.getTestData(TESTDATA_USER_PASSWORD))
                .build();
    }
}
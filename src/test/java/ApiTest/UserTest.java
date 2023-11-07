package ApiTest;

import API.apiMethods.UserApi;
import API.data.UserData;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import API.pojo.UserModel;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class UserTest {
    UserModel user;
    String title = "Welcome";
    boolean isCompleted = true;
    int userId;

    String schemaPath="C:\\untitled\\src\\test\\resources\\schema.json";

    @BeforeClass
    public void setup() {
    }

    @Test
    public void postUser() {
        UserApi.createUser(UserData.createDefaultUserData(title, isCompleted));
    }

    @Test()
    public void readUserAndValidateSchema() {
        ValidatableResponse response = UserApi.createUser(UserData.createDefaultUserData(title, isCompleted));
        user = response.extract().as(UserModel.class);
        UserModel createdUser = UserApi.readParticularUser(user.getId()).extract().as(UserModel.class);
        assertEquals(createdUser.getTitle(), title);
        assertEquals(createdUser.isCompleted(), isCompleted);
        UserApi.readParticularUser(user.getId()).body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }

    @Test()
    public void deleteUser() {
        ValidatableResponse response = UserApi.createUser(UserData.createDefaultUserData(title, isCompleted));
        user = response.extract().as(UserModel.class);
        userId = user.getId();
        UserApi.deleteUser(userId);
        UserApi.readParticularUser(userId).statusCode(404);
    }
    @Test()
    public void updateUser() {
        UserModel user = UserApi.readParticularUser(5).extract().as(UserModel.class);
        user.setTitle("Updated Title");
        user.setCompleted(false);
        UserApi.updateUserWithPut(5);
        assertEquals(user.getTitle(), "Updated Title");
        assertFalse(user.isCompleted());
    }
}

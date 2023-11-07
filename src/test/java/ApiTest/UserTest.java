package ApiTest;

import API.apiMethods.UserApi;
import API.data.UserData;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import API.pojo.UserModel;
import static org.testng.Assert.assertEquals;

public class UserTest {
    UserModel user;
    String title="Welcome";
    boolean isCompleted=true;
    int userId;

    @BeforeClass
    public void setup() {
    }

    @Test
    public void postUser() {
        UserApi.createUser(UserData.createDefaultUserData(title, isCompleted));
    }

    @Test()
    public void readUser() {
        ValidatableResponse response = UserApi.createUser(UserData.createDefaultUserData(title, isCompleted));
        user = response.extract().as(UserModel.class);
        UserModel createdUser = UserApi.readParticularUser(user.getId()).extract().as(UserModel.class);
        assertEquals(createdUser.getTitle(), title);
        assertEquals(createdUser.isCompleted(), isCompleted);
    }
    @Test()
    public void delateUser1() {
        UserModel createdUser = UserApi.deleteUser(5).extract().as(UserModel.class);
//        assertEquals(createdUser.getTitle(), title);
//        assertEquals(createdUser.isCompleted(), isCompleted);
    }


}

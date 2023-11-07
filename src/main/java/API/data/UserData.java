package API.data;

import API.pojo.UserModel;


public class UserData {

   public static UserModel createDefaultUserData(String title, boolean isCompleted) {
        return UserModel.builder().userId(2).title(title).completed(isCompleted).build();
    }
}

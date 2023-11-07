package API.apiMethods;

import API.pojo.UserModel;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class UserApi {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String USER_ENDPOINT = "/todos";
    public static final String SINGLE_USER_ENDPOINT = "/todos/{id}";

    public static ValidatableResponse createUser(UserModel user) {
        return given()
                .spec(requestSpecification())
                .body(user).when()
                .post(USER_ENDPOINT)
                .then()
                .statusCode(201);
    }

    public static ValidatableResponse readUser() {
        return given()
                .spec(requestSpecification())
                .when()
                .get(USER_ENDPOINT)
                .then();
    }

    public static ValidatableResponse deleteUser(int id) {
        return given()
                .spec(requestSpecificationWithParam(id))
                .when()
                .delete(SINGLE_USER_ENDPOINT)
                .then();
    }

    public static ValidatableResponse readParticularUser(int id) {
        return given()
                .log()
                .all()
                .spec(requestSpecificationWithParam(id))
                .when()
                .get(SINGLE_USER_ENDPOINT)
                .then();

    }

    private static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder().setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json").build();
    }

    private static RequestSpecification requestSpecificationWithParam(int id) {
        return given()
                .baseUri(BASE_URL)
                .pathParam("id", id);
    }
}
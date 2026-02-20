package api;

import base.AuthBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthTests extends AuthBaseTest {

    public static String accessToken;
    public static String userId;
    public static String username;

    @Test(priority = 1)
    public void signupTest() {

        username = TestData.randomUsername();

        String requestBody = """
            {
              "first_name": "Chinmay",
              "last_name": "Nahar",
              "user_name": "%s",
              "phone_number": 9999999999,
              "email": "%s",
              "password": "password123"
            }
            """.formatted(username, TestData.email(username));

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post("/auth/signup")
                        .then()
                        .statusCode(anyOf(is(200), is(201)))
                        .extract().response();

        response.prettyPrint(); // keep temporarily

        userId = response.jsonPath().getString("userid"); // adjust if needed

        Assert.assertNotNull(userId, "Signup did not return userId");
    }

    @Test(priority = 2)
    public void loginTest() {

        String requestBody = """
            {
              "username": "%s",
              "password": "password123"
            }
            """.formatted(username);

        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .body("accessToken", notNullValue())
                        .extract().response();

        accessToken = response.jsonPath().getString("accessToken");

        Assert.assertNotNull(accessToken);
    }
}
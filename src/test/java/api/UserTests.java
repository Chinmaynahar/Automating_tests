package api;

import base.UserBaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UserTests extends UserBaseTest {

    @Test(dependsOnMethods = "api.AuthTests.loginTest")
    public void createOrUpdateUser() {

        String requestBody = """
                {
                  "user_id": "%s",
                  "first_name": "Chinmay",
                  "last_name": "Nahar",
                  "phone_number": 9999999999,
                  "email": "chinmay@example.com",
                  "profile_pic": "https://image.url/profile.png"
                }
                """.formatted(AuthTests.userId);

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + AuthTests.accessToken)
                .body(requestBody)
                .when()
                .post("/user/createUpdate")
                .then()
                .statusCode(200);
    }
}
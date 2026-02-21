package api;

import base.ExpenseBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AnyOf.anyOf;

public class ExpenseTests extends ExpenseBaseTest {

    public static String expenseId;

    @Test(dependsOnMethods = "api.UserTests.createOrUpdateUser")
    public void createExpense() {

        String requestBody = """
                {
                  "userId": "%s",
                  "amount": 500,
                  "merchant": "Zomato",
                  "note": "Lunch",
                  "category": "Food",
                  "type": "DEBIT",
                  "transactionAt": "2025-01-20T13:30:00"
                }
                """.formatted(AuthTests.userId);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + AuthTests.accessToken)
                        .body(requestBody)
                        .when()
                        .post("/expense/create")
                        .then()
                        .statusCode(200)
                        .extract().response();

        expenseId = response.jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "createExpense")
    public void deleteExpense() {

        given()
                .header("Authorization", "Bearer " + AuthTests.accessToken)
                .when()
                .delete("/expense/delete/" + expenseId)
                .then()
                .statusCode(200);
    }

    @Test
    public void missingToken(){
        given()
                .header("Authorization", "Bearer ")
                .when()
                .get("expense/get")
                .then()
                .statusCode(400);//missing headers
    }

    @Test
    public void invalidToken(){
        given()
                .header("Authorization", "Bearer " + AuthTests.accessToken+"12345")
                .when()
                .get("expense/get")
                .then()
                .statusCode(500);
    }


}
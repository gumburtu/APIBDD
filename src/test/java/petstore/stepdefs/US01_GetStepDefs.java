package petstore.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class US01_GetStepDefs {
    private Response response;
    private String endpoint;

    @Given("I send a GET request to {string}")
    public void iSendAGETRequestTo(String url) {
        // Set base URI

        baseURI = "https://petstore.swagger.io/v2/";
        
        // Extract endpoint from full URL

        if (url.contains("https://petstore.swagger.io/v2/")) {
            this.endpoint = url.replace("https://petstore.swagger.io/v2/", "");
        } else {
            this.endpoint = url;
        }

        System.out.println("Endpoint to test: " + this.endpoint);
    }

    @When("Get request is sent and response is received")
    public void getRequestIsSentAndResponseIsReceived() {
        response = given()
                .contentType("application/json")
                .when()
                .get(endpoint);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("Status code assertion passed: " + expectedStatusCode);
    }
}
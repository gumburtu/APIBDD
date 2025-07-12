package petstore.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import static petstore.base_url.BaseUrl.spec;

public class US01_SevalGet {
    private Response response;

    @Given("I send a GET request to {string}")
    public void iSendAGETRequestTo(String arg0) {

    }

    @When("Get request is sent and response is received")
    public void getRequestIsSentAndResponseIsReceived() {
        response = spec.given().spec(spec).when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int statusCode) {
        assertEquals(spec, response.getStatusCode());
    }
}

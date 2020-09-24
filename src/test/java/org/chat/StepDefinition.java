package org.chat;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {

    public AppTestServer appTestServer = new AppTestServer(7634);

    private ValidatableResponse validatableResponse;

    @When("^call GET \"([^\"]*)\"$")
    public void callGET(String url) {
        appTestServer.before();
        validatableResponse = get(url).then().assertThat();
        appTestServer.after();
    }

    @Then("^status (\\d+) and response \"([^\"]*)\"$")
    public void status_and_response(int status, String body) {
        validatableResponse
                .statusCode(status)
                .body(equalTo(body));
    }
}

package org.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;

import java.io.IOException;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class StepDefinition {

    public AppTestServer appTestServer = new AppTestServer(7634);

    private ObjectMapper objectMapper = new ObjectMapper();
    private ValidatableResponse validatableResponse;

    @When("^call GET \"([^\"]*)\"$")
    public void callGET(String url) {
        appTestServer.before();
        validatableResponse = get(url).then().assertThat();
        appTestServer.after();
    }

    @When("^call POST \"([^\"]*)\" with json content (.*)$")
    public void call_POST_with_json_content(String url, String jsonContent) throws Throwable {
        appTestServer.before();
        validatableResponse = given()
                .body(asJsonNode(jsonContent).toString())
                .contentType("application/json")
                .post(url)
                .then().assertThat();
        appTestServer.after();
    }

    @Then("^status (\\d+)$")
    public void status(int status) {
        validatableResponse.statusCode(status);

    }

    @Then("^response is a json (.*)$")
    public void response(String json) throws IOException {
        validatableResponse.contentType("application/json");
        String body = validatableResponse.extract().body().asString();
        JsonNode expected = asJsonNode(json);
        JsonNode actual = asJsonNode(body);

        Assertions.assertThat(actual)
                .withFailMessage("JSON body mismatch!\n\nactual    " + actual + "\nexpected  " + expected)
                .isEqualTo(expected);
    }

    private JsonNode asJsonNode(String json) throws IOException {
        return objectMapper.readTree(json);
    }
}

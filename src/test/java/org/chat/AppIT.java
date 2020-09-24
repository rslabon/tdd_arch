package org.chat;

import org.junit.Rule;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class AppIT {

    @Rule
    public AppITRule appRule = new AppITRule(7634);

    @Test
    public void appIsRunning() {
        get("/hello")
                .then().assertThat()
                .statusCode(200)
                .body(equalTo("Hello World!"));
    }
}

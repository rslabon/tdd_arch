package org.chat;

import io.restassured.RestAssured;
import org.chat.main.infrastructure.App;
import org.junit.rules.ExternalResource;

public class AppTestServer extends ExternalResource {

    private final int port;
    private App app;

    public AppTestServer(int port) {
        this.port = port;
    }

    @Override
    protected void before() {
        app = new App();
        app.init(port);
        app.setUpRoutes();
        app.waitForInit();
        RestAssured.port = port;
    }

    @Override
    protected void after() {
        app.stop();
    }
}

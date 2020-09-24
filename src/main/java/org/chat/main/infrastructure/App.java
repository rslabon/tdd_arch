package org.chat.main.infrastructure;

import spark.Spark;

public class App {

    public void init(int port) {
        Spark.port(port);
    }

    public void setUpRoutes() {
        Spark.get("/hello", (req, res) -> "Hello World!");
    }

    public void waitForInit() {
        Spark.awaitInitialization();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}

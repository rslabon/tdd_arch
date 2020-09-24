package org.chat.main.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Spark;

public class App {

    public void init(int port) {
        Spark.port(port);
    }

    public void setUpRoutes() {

        Spark.get("/hello", (req, res) -> {
            res.type("application/json");
            return "{\"message\": \"hello world!\"}";
        });

        Spark.post("/hello", (req, res) -> {
            res.type("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode content = objectMapper.readTree(req.body());
            String name = content.get("name").textValue();
            return "{\"message\": \"hello " + name + "!\"}";
        });

    }

    public void waitForInit() {
        Spark.awaitInitialization();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}

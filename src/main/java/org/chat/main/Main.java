package org.chat.main;


import org.chat.main.infrastructure.App;

public class Main {
    public static void main(String[] args) {
        start(8080);
    }

    public static void start(int port) {
        App app = new App();
        app.init(port);
        app.setUpRoutes();
        app.waitForInit();
    }
}

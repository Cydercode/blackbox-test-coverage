package com.cydercode.microservice;

public class App {

    public static void main(String[] args) {
        Database database = new Database();
        Server server = new Server(8080);
        server.setDatabase(database);
        server.start();
    }
}

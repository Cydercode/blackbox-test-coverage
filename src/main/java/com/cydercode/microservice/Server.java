package com.cydercode.microservice;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.stream.Collectors;

public class Server {

    public static final String OK_RESPONSE = "OK";
    private Database database;

    public Server(int port) {
        Spark.port(port);
    }

    public void start() {
        Spark.get("/", (req, res) -> "Hello MicroService!");

        Spark.post("/items", this::add);
        Spark.delete("/item", this::remove);
        Spark.delete("/allItems", this::removeAll);
        Spark.get("/items", this::get);
        Spark.get("/stats", this::getStats);
    }

    private String removeAll(Request request, Response response) {
        database.removeAll();
        return OK_RESPONSE;
    }

    private Object getStats(Request request, Response response) {
        return database.getStatistics().toString();
    }

    private String get(Request request, Response response) {
        return database.getAll().stream().collect(Collectors.joining(", "));
    }

    private String remove(Request request, Response response) {
        database.remove(request.body());
        return OK_RESPONSE;
    }

    private String add(Request req, Response res) {
        try {
            database.add(req.body());
            return OK_RESPONSE;
        } catch (Exception e) {
            res.status(500);
            return "ERROR: " + e.getMessage();
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }
}

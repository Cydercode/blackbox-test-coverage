package com.cydercode.microservice;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Database {

    private final List<String> database = new CopyOnWriteArrayList<>();
    private final DatabaseStatistics statistics = new DatabaseStatistics();

    public void add(String item) {
        statistics.add();
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null!");
        }

        database.add(item);
    }

    public void removeAll() {
        database.clear();
    }

    public void remove(String item) {
        statistics.delete();
        database.remove(item);
    }

    public List<String> getAll() {
        statistics.get();
        return new ArrayList<>(database);
    }

    public Map<String, Integer> getStatistics() {
        return ImmutableMap.<String, Integer>builder()
                .put("addOperations", statistics.getAddOperations())
                .put("removeOperations", statistics.getDeleteOperations())
                .put("getOperations", statistics.getGetOperations())
                .build();
    }
}

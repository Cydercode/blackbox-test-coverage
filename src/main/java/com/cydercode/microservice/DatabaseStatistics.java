package com.cydercode.microservice;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseStatistics {

    private final AtomicInteger addOperations = new AtomicInteger(0);
    private final AtomicInteger getOperations = new AtomicInteger(0);
    private final AtomicInteger deleteOperations = new AtomicInteger();

    public void add() {
        addOperations.incrementAndGet();
    }

    public void get() {
        getOperations.incrementAndGet();
    }

    public void delete() {
        deleteOperations.incrementAndGet();
    }

    public int getAddOperations() {
        return addOperations.get();
    }

    public int getGetOperations() {
        return getOperations.get();
    }

    public int getDeleteOperations() {
        return deleteOperations.get();
    }
}

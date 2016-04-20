package com.example.herotest.utils;

public class IDGenerator {
private static int id=0;
    public synchronized static int generateId() {
        return ++id;
    }

}

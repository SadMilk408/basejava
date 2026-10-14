package com.sadmilk.webapp.model;

public record Resume(
        String uuid
) {
    @Override
    public String toString() {
        return uuid;
    }
}
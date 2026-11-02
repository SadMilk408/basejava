package com.sadmilk.webapp.model;

import java.util.Objects;
import java.util.UUID;

public record Resume(String uuid, String fullName) {
    public Resume() {
        this(UUID.randomUUID().toString(), "");
    }

    public Resume(String uuid) {
        this(uuid, "");
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
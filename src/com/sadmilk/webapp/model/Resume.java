package com.sadmilk.webapp.model;

import java.util.Objects;
import java.util.UUID;

public record Resume(String uuid) implements Comparable<Resume> {
    public Resume() {
        this(UUID.randomUUID().toString());
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

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
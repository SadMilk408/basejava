package com.sadmilk.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class WorkPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public WorkPeriod(LocalDate startDate, LocalDate endDate, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkPeriod that = (WorkPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, description);
    }

    @Override
    public String toString() {
        return "WorkPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}

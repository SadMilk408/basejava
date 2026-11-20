package com.sadmilk.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final String title;

    private final List<WorkPeriod> workPeriods;

    public Organization(String name, String url, String title, List<WorkPeriod> workPeriods) {
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.title = title;
        this.workPeriods = workPeriods;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(title, that.title) && Objects.equals(workPeriods, that.workPeriods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, title, workPeriods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", title='" + title + '\'' +
                ", workPeriods=" + workPeriods +
                '}';
    }
}

package com.sadmilk.webapp.model;

import com.sadmilk.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;

    private final List<WorkPeriod> workPeriods;

    public Organization(String name, String url, WorkPeriod... workPeriods) {
        this(new Link(name, url), Arrays.asList(workPeriods));
    }

    public Organization(Link homePage, List<WorkPeriod> workPeriods) {
        this.homePage = homePage;
        this.workPeriods = workPeriods;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(workPeriods, that.workPeriods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, workPeriods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", workPeriods=" + workPeriods +
                '}';
    }

    // static потому что не зависит от родителя
    public static class WorkPeriod {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public WorkPeriod(int startYear, Month startMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.FUTURE_DATE, title, description);
        }

        public WorkPeriod(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
        }

        public WorkPeriod(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "WorkPeriod{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            WorkPeriod that = (WorkPeriod) o;
            return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }
    }
}

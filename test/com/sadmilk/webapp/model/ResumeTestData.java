package com.sadmilk.webapp.model;

import java.time.Month;
import java.util.ArrayList;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(
                uuid,
                fullName
        );

        resume.addContacts(ContactType.GITHUB, new TextSection("github.com/SadMilk408"));
        resume.addContacts(ContactType.HOME_PHONE, new TextSection("89090854572"));
        resume.addContacts(ContactType.MAIL, new TextSection("homyku408@gmail.com"));

        var organizations = new ArrayList<Organization>();

        organizations.add(new Organization(
                "123",
                null,
                new Organization.WorkPeriod(
                        2024, Month.MAY,
                        2026, Month.OCTOBER,
                        "123", ""),
                new Organization.WorkPeriod(
                        2026, Month.OCTOBER,
                        "1234", "")));

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(organizations));

        return resume;
    }

    public static void main(String[] args) {
        var resume = createResume("uuid-123", "test");

        System.out.println(resume);
    }
}

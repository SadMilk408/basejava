package com.sadmilk.webapp.model;

import com.sadmilk.webapp.util.DateUtil;

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
        var workPeriods = new ArrayList<WorkPeriod>();

        workPeriods.add(
                new WorkPeriod(
                        DateUtil.of(2024, Month.MAY),
                        DateUtil.of(2026, Month.NOVEMBER),
                        "123"));

        organizations.add(new Organization(
                "123",
                null,
                "123",
                workPeriods));

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(organizations));

        return resume;
    }

    public static void main(String[] args) {
        var resume = createResume("uuid-123", "test");

        System.out.println(resume);
    }
}

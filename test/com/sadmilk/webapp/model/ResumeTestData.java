package com.sadmilk.webapp.model;

import com.sadmilk.webapp.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume(
                "uuid-123",
                "Иван Иванов"
        );

        resume.addContacts(ContactType.GITHUB, new TextSection("github.com/SadMilk408"));
        resume.addContacts(ContactType.HOME_PHONE, new TextSection("89090854572"));
        resume.addContacts(ContactType.MAIL, new TextSection("homyku408@gmail.com"));

        var organizations = new ArrayList<Organization>();

        organizations.add(new Organization("123", "123.ru", DateUtil.of(2024, Month.MAY), DateUtil.of(2026, Month.NOVEMBER), "123", "123"));

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(organizations));

        System.out.println(resume);
    }
}

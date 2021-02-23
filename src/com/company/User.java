package com.company;

import javax.management.Notification;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class User extends Consumer implements Observer{
    ArrayList<String> interested_companies = new ArrayList<>();
    ArrayList<String> notifications = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();

    public void setInterested_companies(ArrayList<String> interested_companies) {
        this.interested_companies = interested_companies;
    }

    User() {
        setPassword("user");
    }

    User(Resume resume) {
        super(resume);
        setPassword("user");
    }

    User(Resume resume, LinkedList<Consumer> consumers) {
        super(resume, consumers);
        setPassword("user");
    }

    public Employee convert() {
        Employee employee = new Employee(getResume(), getConsumers());
        return employee;
    }

    public Double getTotalScore() {
        return getExperienceYears() * 1.5 + meanGPA();
    }

    public void update(String notification) {
        notifications.add(notification);
    }

    public String getNotifications(){
        StringBuffer s = new StringBuffer();
        for(String string : notifications){
            s.append(string).append("\n\n");
        }
        return s.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(interested_companies, user.interested_companies);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), interested_companies);
    }

    public String toString() {
        return "User{" +
                "interested_companies=" + interested_companies+ "\n" + super.toString() +
                '}';
    }
}

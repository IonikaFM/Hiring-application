package com.company;

import javax.swing.*;
import java.awt.desktop.AppForegroundListener;
import java.util.LinkedList;
import java.util.Objects;

public class Recruiter extends Employee implements Comparable{
    private Double rating;

    Recruiter() {
        super();
        rating = 5.;
    }

    Recruiter(String company, int salary, String jobName, Resume resume){
        super(company, salary, jobName, resume);
        rating = 5.;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public double evaluate(Job job, User user) {
        double note = 0;
        Company company = Application.getInstance().getCompany(job.getCompany());
        if(company == null){
            System.out.println("null");
        return 0;}
        if(job.meetsRequirments(user)) {
            note = user.getTotalScore() * rating;
            Request request = new Request(job, user, this, note);
            company.getManager().add(request);
            job.addCandidate(user);
        }
        else {
                company.notifyAllObservers ( "User " + user.getResume().information.getName()
                    + " is not eligible for the job \"" + job.getJobName() + "\" from the " + job.getCompany() +
                    " company!");
        }
        rating += 0.1;
        return note;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recruiter)) return false;
        if (!super.equals(o)) return false;
        Recruiter recruiter = (Recruiter) o;
        return Objects.equals(rating, recruiter.rating);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), rating);
    }

    public String toString() {
        return "Recruiter{" +
                "rating=" + rating + super.toString() +
                '}';
    }

    public int compareTo(Object o) {
        return this.rating.compareTo(((Recruiter)o).getRating());
    }
}

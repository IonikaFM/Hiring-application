package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Employee extends Consumer{
    private String companyName;
    private String jobName;
    private double salary;

    Employee() {
        super();
        companyName = new String("");
        jobName = new String("");
        salary = 0;
    }

    Employee(String companyName, double salary, String jobName) {
        super();
        this.companyName = companyName;
        this.salary = salary;
        this.jobName = jobName;
    }

    Employee(String companyName, double salary, String jobName, Resume resume) {
        super(resume);
        this.companyName = companyName;
        this.salary = salary;
        this.jobName = jobName;
    }

    Employee(String companyName, double salary, String jobName, Resume resume, LinkedList<Consumer> consumers) {
        super(resume, consumers);
        this.companyName = companyName;
        this.salary = salary;
        this.jobName = jobName;
    }

    Employee(Resume resume, LinkedList<Consumer> consumers) {
        super(resume, consumers);
        companyName = new String("");
        salary = 0;
        this.jobName = new String("");
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String company) {
        this.companyName = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(companyName, employee.companyName) && Objects.equals(jobName, employee.jobName);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName, jobName, salary);
    }

    /*public String toString() {
        return "Employee{" +
                "Company= '" + companyName + '\'' +
                "jobName=" + jobName +
                ", salary=" + salary +
                super.toString() + "\n" + '}';
    }*/
}

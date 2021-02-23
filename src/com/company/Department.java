package com.company;

import java.util.ArrayList;

public abstract class Department {
    private ArrayList<Employee> employees;
    private ArrayList<Job> jobs;
    private String name;

    Department(){
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
        name = new String();
    }

    Department(ArrayList<Employee> employees, ArrayList<Job> jobs, String name){
        this.employees = employees;
        this.jobs = jobs;
        this.name = name;
    }

    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs(){
        return jobs;
    }

    public void add(Employee employee){
        employees.add(employee);
    }

    public void remove(Employee employee){
        employees.remove(employee);
    }

    public void add(Job job){
        jobs.add(job);
        Application application = Application.getInstance();
        for(Company company : application.getCompanies()){
            if(company.getCompanyName().equals(job.getCompany())){
                company.notifyAllObservers("A new job has been added to company " +
                        company.getCompanyName() + " that you are following!");
                System.out.println();
            }
        }
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public void remove(Job job) { jobs.remove(job);}

    public String getName() {
        return name;
    }

    public String toString() {
        return "Department{" + name +
                ", employees=" + employees +
                ", jobs=" + jobs +
                '}';
    }
}

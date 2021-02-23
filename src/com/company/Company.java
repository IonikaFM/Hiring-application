package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Company implements Subject{
    private String companyName;
    private Manager manager;
    private ArrayList<Department> departments;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<User> observers;

    Company(){
        companyName = new String("");
        manager = new Manager();
        departments = new ArrayList<>();
        recruiters = new ArrayList<>();
        observers = new ArrayList<>();
    }

    Company(String companyName, Manager manager, ArrayList<Department> departments, ArrayList<Recruiter> recruiters){
        this.companyName = companyName;
        this.manager = manager;
        this.departments = departments;
        this.recruiters = recruiters;
        observers = new ArrayList<>();
    }

    public ArrayList<User> getObservers() {
        return observers;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(ArrayList<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public void add(Department department){
        departments.add(department);
    }

    public void add(Recruiter recruiter){
        recruiters.add(recruiter);
        Collections.sort(recruiters);
    }

    public void add(Employee employee, Department department){
        departments.get(departments.indexOf(department)).add(employee);
    }

    public void remove(Employee employee){
        for(Department i : departments){
            for(Employee j : i.getEmployees()){
                if(j.equals(employee))
                    i.remove(employee);
            }
        }
    }

    public void remove(Department department){
        for(Department i : departments){
            if(i.equals(department)) {
                for (Employee j : i.getEmployees()) {
                    remove(j);
                }
                departments.remove(department);
            }
        }
    }

    public void remove(Recruiter recruiter){
        for(Department i : departments){
            for(Employee j : i.getEmployees()){
                if(j.equals(recruiter))
                    i.remove(recruiter);
            }
        }
        recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination){
        for(Job job : source.getJobs()){
            destination.add(job);
            source.remove(job);
        }
        for(Employee employee : source.getEmployees()){
            destination.add(employee);
            source.remove(employee);
        }
    }

    public void move(Employee employee, Department newDepartment){
        for(Department i : departments){
            for(Employee j : i.getEmployees()){
                if(j.equals(employee))
                    i.remove(employee);
            }
        }
        newDepartment.add(employee);
    }

    public boolean contains(Department department){
        return departments.contains(department);
    }

    public boolean contains(Employee employee){
        for(Department i : departments){
            for(Employee j : i.getEmployees()){
                if(j.equals(employee))
                    return true;
            }
        }
        return false;
    }

    public boolean contains(Recruiter recruiter){
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user){
        double degree = -1;
        Recruiter recruiter = new Recruiter();
        for(Recruiter r : recruiters){
            if(user.getDegreeInFriendship(r) > degree){
                recruiter = r;
                degree = user.getDegreeInFriendship(r);
            } else if (user.getDegreeInFriendship(r) == degree) {
                if(r.getRating() > recruiter.getRating()){
                    recruiter = r;
                }
            }
        }
        if(degree != -1)
            return recruiter;
        for(Recruiter r : recruiters){
            if(r.getRating() > degree) {
                degree = r.getRating();
                recruiter = r;
            }
        }
        return recruiter;
    }

    public ArrayList<Job> getJobs(){
        ArrayList<Job> jobs = new ArrayList<>();
        for(Department i : departments) {
            for(Job j : i.getJobs()) {
                if(j.getFlag())
                    jobs.add(j);
            }
        }
        return jobs;
    }

    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", manager=" + manager +
                ", departments=" + departments +
                ", recruiters=" + recruiters +
                '}';
    }

    public void addObserver(User user) {
        if(!observers.contains(user))
            observers.add(user);
    }

    public void removeObserver(User user) {
        if(observers.contains(user))
            observers.remove(user);
    }

    public void notifyAllObservers(String notification) {
        for(User user : observers){
            user.update(notification);
        }
    }
}

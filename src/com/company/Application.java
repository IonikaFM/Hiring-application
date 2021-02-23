package com.company;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Application instance = null;
    private ArrayList<Company> companies;
    private ArrayList<User> users;

    private Application(){
        companies = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static Application getInstance(){
        if(instance == null){
            instance = new Application();
        }
        return instance;
    }

    public ArrayList<Company> getCompanies(){
        return companies;
    }

    public Company getCompany(String name){
        for(Company i: companies){
            if(i.getCompanyName().equals(name))
                return i;
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addCompany(Company company){
        companies.add(company);
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean remove(Company company){
        for(Company i:companies){
            if(i.equals(company)){
                companies.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean remove(User user){
        for(User i: users){
            if(i.equals(user)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Job> getJobs(List<String> companies){
        ArrayList<Job> jobs = new ArrayList<>();
        for(String i: companies){
            for(Company j: this.companies){
                if(getCompany(i).equals(j)){
                    jobs.addAll(j.getJobs());
                }
            }
        }
        return jobs;
    }

    public String toString() {
        return "---------------Application-------------- \n { \n" +
                "------------------COMPANIES :---------------------- \n" + companies +
                "\n\n----------------------------------USERS : --------------------------\n" + users +
                "\n";
    }
}

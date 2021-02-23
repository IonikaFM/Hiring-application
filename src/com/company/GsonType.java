package com.company;

import java.util.ArrayList;

public class GsonType {
    public ArrayList<GsonConsumer> employees;
    public ArrayList<GsonConsumer> recruiters;
    public ArrayList<GsonConsumer> users;
    public ArrayList<GsonConsumer> managers;

    GsonType(ArrayList<GsonConsumer> employees, ArrayList<GsonConsumer> recruiters, ArrayList<GsonConsumer> users,
             ArrayList<GsonConsumer> managers){
        this.employees = employees;
        this.recruiters = recruiters;
        this.users = users;
        this.managers = managers;
    }

    public ArrayList<GsonConsumer> getEmployees() {
        return employees;
    }

    public ArrayList<GsonConsumer> getRecruiters() {
        return recruiters;
    }

    public ArrayList<GsonConsumer> getUsers() {
        return users;
    }

    public ArrayList<GsonConsumer> getManagers() {
        return managers;
    }
}

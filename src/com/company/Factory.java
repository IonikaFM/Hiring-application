package com.company;

import java.util.ArrayList;

public class Factory {
    public Department getDepartment(String department, ArrayList<Employee> employees, ArrayList<Job> jobs){
        if(department.equals("IT")){
            return new IT(employees, jobs);
        }
        if(department.equals("Management")){
            return new Management(employees, jobs);
        }
        if(department.equals("Marketing")){
            return new Marketing(employees, jobs);
        }
        if(department.equals("Finance")){
            return new Finance(employees, jobs);
        }
        return null;
    }
}
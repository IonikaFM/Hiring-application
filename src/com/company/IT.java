package com.company;

import java.util.ArrayList;

public class IT extends Department{
    IT(ArrayList<Employee> employees, ArrayList<Job> jobs){
        super(employees, jobs, "IT");
    }

    IT(){}

    public double getTotalSalaryBudget() {
        double salary = 0;

        for(Employee employee : getEmployees()){
            salary += employee.getSalary();
        }

        return salary;
    }
}

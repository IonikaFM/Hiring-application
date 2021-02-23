package com.company;

import java.util.ArrayList;

public class Finance extends Department{
    Finance(ArrayList<Employee> employees, ArrayList<Job> jobs){
        super(employees, jobs, "Finance");
    }

    Finance(){}

    public double getTotalSalaryBudget() {
        double salary = 0;

        for(Employee employee : getEmployees()){
            if(employee.getExperienceYears() < 1)
                salary += employee.getSalary() + (10/100 * employee.getSalary());
            else
                salary += employee.getSalary() + (16/100 * employee.getSalary());
        }

        return salary;
    }
}

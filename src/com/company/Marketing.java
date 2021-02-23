package com.company;

import java.util.ArrayList;

public class Marketing extends Department{
    Marketing(ArrayList<Employee> employees, ArrayList<Job> jobs){
        super(employees, jobs, "Marketing");
    }

    Marketing(){}

    public double getTotalSalaryBudget() {
        double salary = 0;

        for(Employee employee : getEmployees()){
            if(employee.getSalary() > 5000)
                salary += employee.getSalary() + (10/100 * employee.getSalary());
            else if(employee.getSalary() > 3000)
                salary += employee.getSalary() + (16/100 * employee.getSalary());
        }

        return salary;
    }
}

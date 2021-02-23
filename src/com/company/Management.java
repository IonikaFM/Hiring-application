package com.company;

import java.util.ArrayList;

public class Management extends Department{
    Management(ArrayList<Employee> employees, ArrayList<Job> jobs){
        super(employees, jobs, "Management");
    }

    Management(){}

    public double getTotalSalaryBudget() {
        double salary = 0;

        for(Employee employee : getEmployees()){
            salary += employee.getSalary();
        }

        return salary + (16/100 * salary);
    }
}

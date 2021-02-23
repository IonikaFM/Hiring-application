package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminWindow extends JFrame{
    JComboBox<String> jComboBox_companies;
    JComboBox<String> jComboBox_departments;
    JPanel admin;
    JPanel company;
    JLabel jLabel;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JLabel jLabel5;
    JList jList;
    JButton jButton;
    JButton jButton_employees;
    JButton jButton_jobs;
    JButton log_out;
    JTextArea jTextArea_employees;
    JTextArea jTextArea_jobs;
    JTextArea invisible;
    JTextArea invisible1;
    JTextArea invisible2;
    JTextArea invisible3;
    JTextArea invisible4;
    JTextArea invisible5;
    JTextArea salary_budget;


    AdminWindow(){
        super("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        setLocationRelativeTo(null);

        company = new JPanel();
        company.setLayout(new GridBagLayout());
        company.setVisible(false);
        add(company);

        jLabel3 = new JLabel("Select one department to see it's details :");
        jLabel4 = new JLabel("Select one of the following :");
        jLabel5 = new JLabel("Total salary budget :");
        salary_budget = new JTextArea(1, 15);
        salary_budget.setEditable(false);
        salary_budget.setOpaque(false);
        invisible = new JTextArea(2,1);
        invisible1 = new JTextArea(3,1);
        invisible2 = new JTextArea(2,1);
        invisible3 = new JTextArea(2,1);
        invisible4 = new JTextArea(2,1);
        invisible5 = new JTextArea(2,1);
        invisible.setOpaque(false);
        invisible1.setOpaque(false);
        invisible2.setOpaque(false);
        invisible3.setOpaque(false);
        invisible4.setOpaque(false);
        invisible5.setOpaque(false);
        invisible.setEditable(false);
        invisible1.setEditable(false);
        invisible2.setEditable(false);
        invisible3.setEditable(false);
        invisible4.setEditable(false);
        invisible5.setEditable(false);
        jTextArea_employees = new JTextArea("There are no empoyees in this department !");
        jTextArea_employees.setVisible(false);
        jTextArea_jobs = new JTextArea("There are no available jobs in this department !");
        jTextArea_jobs.setEditable(false);
        jTextArea_jobs.setOpaque(false);
        jTextArea_jobs.setVisible(false);
        EmployeesPanel employeesPanel = new EmployeesPanel();
        employeesPanel.setVisible(false);
        JobsPanel jobsPanel = new JobsPanel();
        jobsPanel.setVisible(false);
        jButton_employees = new JButton("Employees");
        jButton_jobs = new JButton("Jobs");
        log_out = new JButton("Log out");

        log_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProfileWindow profileWindow = new ProfileWindow();
                setVisible(false);
            }
        });

        jComboBox_departments = new JComboBox<>();
        jComboBox_departments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Company c = Application.getInstance().getCompany((String)jComboBox_companies.getSelectedItem());
                employeesPanel.setPanel();
                jobsPanel.setPanel();
                for(Department department : c.getDepartments()){
                    if(department.getName().equals(jComboBox_departments.getSelectedItem())){
                        employeesPanel.setPanel(department);
                        jobsPanel.setPanel(department);
                        salary_budget.setText(String.valueOf(department.getTotalSalaryBudget()));
                        break;
                    }
                }
                employeesPanel.setVisible(false);
                jobsPanel.setVisible(false);
                jTextArea_jobs.setVisible(false);
                jTextArea_employees.setVisible(false);
            }
        });

        company.add(jLabel3, gbc);
        company.add(jComboBox_departments, gbc);
        company.add(invisible, gbc);
        company.add(jTextArea_jobs, gbc);
        company.add(jTextArea_employees, gbc);
        company.add(employeesPanel, gbc);
        company.add(jobsPanel, gbc);

        jButton_employees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jobsPanel.setVisible(false);
                jTextArea_jobs.setVisible(false);
                if(employeesPanel.jComboBox_employees.getItemCount() == 0)
                {
                    jTextArea_employees.setVisible(true);
                    employeesPanel.setVisible(false);
                } else {
                    jTextArea_employees.setVisible(false);
                    employeesPanel.setVisible(true);
                }
            }
        });

        jButton_jobs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jobsPanel.jComboBox_jobs.getItemCount() == 0){
                    jTextArea_jobs.setVisible(true);
                    jobsPanel.setVisible(false);
                } else {
                    jTextArea_jobs.setVisible(false);
                    jobsPanel.setVisible(true);
                }
                employeesPanel.setVisible(false);
                jTextArea_employees.setVisible(false);
            }
        });

        company.add(invisible2, gbc);
        company.add(jLabel4);
        company.add(jButton_employees);
        company.add(jButton_jobs, gbc);

        jButton = new JButton("Click here to look for another company !");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                company.setVisible(false);
                admin.setVisible(true);
                jComboBox_departments.removeAllItems();
            }
        });
        company.add(invisible5, gbc);
        company.add(jLabel5);
        company.add(salary_budget, gbc);
        company.add(invisible1, gbc);
        company.add(jButton, gbc);

        admin = new JPanel();
        admin.setLayout(new GridBagLayout());
        add(admin);

        jLabel = new JLabel("Companies :");
        jLabel1 = new JLabel("Users :");
        jLabel2 = new JLabel("Select a company from the list below to see details about it");

        admin.add(jLabel2, gbc);

        admin.add(jLabel);
        jComboBox_companies = new JComboBox<>();
        for(Company company : Application.getInstance().getCompanies()){
            jComboBox_companies.addItem(company.getCompanyName());
        }
        jComboBox_companies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                admin.setVisible(false);
                company.setVisible(true);
                Company c = Application.getInstance().getCompany((String)jComboBox_companies.getSelectedItem());
                for(Department department : c.getDepartments()) {
                    jComboBox_departments.addItem(department.getName());
                }
            }
        });
        admin.add(jComboBox_companies, gbc);
        admin.add(invisible3, gbc);

        admin.add(jLabel1);
        DefaultListModel defaultListModel = new DefaultListModel();
        for(User user : Application.getInstance().getUsers()){
            defaultListModel.addElement(user.getResume().information.getName());
        }
        jList = new JList(defaultListModel);
        admin.add(jList, gbc);
        admin.add(invisible4, gbc);
        admin.add(log_out, gbc);

        pack();
        setVisible(true);
    }
}

class EmployeesPanel extends JPanel{
    JComboBox<String> jComboBox_employees;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;

    EmployeesPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        l4 = new JLabel("Select an employee :");
        jComboBox_employees = new JComboBox<>();
        l1 = new JLabel("Name : ");
        l2 = new JLabel("Job name : ");
        l3 = new JLabel("Salary : ");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);

        add(l4);
        add(jComboBox_employees, gbc);
        add(l1);
        add(t1, gbc);
        add(l2);
        add(t2, gbc);
        add(l3);
        add(t3, gbc);
    }

    public void setPanel(Department department){
        for(Employee employee : department.getEmployees()){
            jComboBox_employees.addItem(employee.getResume().information.getName());
        }
        if(department.getEmployees().size() != 0) {
            Employee employee = department.getEmployees().get(0);
            t1.setText(employee.getResume().information.getName());
            t2.setText(employee.getJobName());
            t3.setText(String.valueOf(employee.getSalary()));
        }

        jComboBox_employees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Employee employee: department.getEmployees()){
                    if(employee.getResume().information.getName().equals(jComboBox_employees.getSelectedItem())){
                        t1.setText(employee.getResume().information.getName());
                        t2.setText(employee.getJobName());
                        t3.setText(String.valueOf(employee.getSalary()));
                        break;
                    }
                }
            }
        });
    }

    public void setPanel(){
        jComboBox_employees.removeAllItems();
    }
}

class JobsPanel extends JPanel{
    JComboBox<String> jComboBox_jobs;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea t4;

    JobsPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        l5 = new JLabel("Select a job :");
        jComboBox_jobs = new JComboBox<>();
        l1 = new JLabel("Job name : ");
        l2 = new JLabel("Company name : ");
        l3 = new JLabel("Number of available positions : ");
        l4 = new JLabel("Salary : ");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t4 = new JTextArea();
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        t4.setOpaque(false);
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        add(l5);
        add(jComboBox_jobs, gbc);
        add(l1);
        add(t1, gbc);
        add(l2);
        add(t2, gbc);
        add(l3);
        add(t3, gbc);
        add(l4);
        add(t4, gbc);
    }

    public void setPanel(Department department){
        for(Job job : department.getJobs()){
            jComboBox_jobs.addItem(job.getJobName());
        }

        if(department.getJobs().size() != 0){
            Job job = department.getJobs().get(0);
            t1.setText(job.getJobName());
            t2.setText(job.getCompany());
            t3.setText(String.valueOf(job.getNumberCandidatesNeeded()));
            t4.setText(String.valueOf(job.getSalary()));
        }

        jComboBox_jobs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Job job : department.getJobs()){
                    if(job.getJobName().equals(jComboBox_jobs.getSelectedItem())){
                        t1.setText(job.getJobName());
                        t2.setText(job.getCompany());
                        t3.setText(String.valueOf(job.getNumberCandidatesNeeded()));
                        t4.setText(String.valueOf(job.getSalary()));
                        break;
                    }
                }
            }
        });
    }

    public void setPanel(){
        jComboBox_jobs.removeAllItems();
    }
}
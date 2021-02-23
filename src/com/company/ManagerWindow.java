package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerWindow extends JFrame {
    JButton log_out;
    ManagerPanel managerPanel;
    JTabbedPane jTabbedPane;
    JPanel jPanel;
    JTextArea i;
    JTextArea i1;
    JTextArea i2;
    int index = 0;
    RecruiterListPanel recruiters;

    ManagerWindow(Manager manager){
        super("Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        setLocationRelativeTo(null);

        i = new JTextArea(2, 0);
        i1 = new JTextArea(2, 0);
        i2 = new JTextArea(2, 0);
        i.setEditable(false);
        i1.setEditable(false);
        i2.setEditable(false);
        i.setOpaque(false);
        i1.setOpaque(false);
        i2.setOpaque(false);

        jTabbedPane = new JTabbedPane();
        add(jTabbedPane, gbc);

        managerPanel = new ManagerPanel(manager);
        jTabbedPane.add("Requests", managerPanel);

        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jTabbedPane.add("Company", jPanel);

        recruiters = new RecruiterListPanel(Application.getInstance().getCompany(manager.getCompanyName()));
        recruiters.setLayout(new GridBagLayout());
        jTabbedPane.add("Recruiters", recruiters);

        jTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(index != 2 && jTabbedPane.getSelectedIndex() == 2){
                    recruiters = new RecruiterListPanel(Application.getInstance().getCompany(manager.getCompanyName()));
                }
                index = jTabbedPane.getSelectedIndex();
            }
        });

        JTextArea jTextArea_jobs = new JTextArea("There are no available jobs in this department !");
        jTextArea_jobs.setVisible(false);
        jTextArea_jobs.setOpaque(false);
        jTextArea_jobs.setEditable(false);

        log_out = new JButton("Log out");

        log_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProfileWindow profileWindow = new ProfileWindow();
                setVisible(false);
            }
        });

        add(log_out, gbc);

        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setOpaque(false);
        jTextArea.setText(manager.getCompanyName());
        jPanel.add(jTextArea, gbc);
        jPanel.add(i2, gbc);
        JComboBox<String> jComboBox_departments = new JComboBox<>();
        JLabel jLabel = new JLabel("Select one department : ");
        jPanel.add(jLabel);
        jPanel.add(jComboBox_departments, gbc);
        jPanel.add(i, gbc);
        EmployeesPanel employeesPanel = new EmployeesPanel();
        employeesPanel.setPanel(Application.getInstance().getCompany(manager.getCompanyName()).getDepartments().get(0));
        JobsPanel jobsPanel = new JobsPanel();
        jobsPanel.setPanel(Application.getInstance().getCompany(manager.getCompanyName()).getDepartments().get(0));
        jPanel.add(employeesPanel, gbc);
        jPanel.add(i1, gbc);
        jPanel.add(jobsPanel, gbc);
        jPanel.add(jTextArea_jobs, gbc);

        for(Department department : Application.getInstance().getCompany(manager.getCompanyName()).getDepartments()){
            jComboBox_departments.addItem(department.getName());
        }
        jComboBox_departments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Company c = Application.getInstance().getCompany(manager.getCompanyName());
                employeesPanel.setPanel();
                jobsPanel.setPanel();
                for(Department department : c.getDepartments()){
                    if(department.getName().equals(jComboBox_departments.getSelectedItem())){
                        employeesPanel.setPanel(department);
                        jobsPanel.setPanel(department);
                        employeesPanel.setVisible(true);
                        jobsPanel.setVisible(true);
                        jTextArea_jobs.setVisible(false);
                        if(jobsPanel.jComboBox_jobs.getItemCount() == 0) {
                            jTextArea_jobs.setVisible(true);
                            jobsPanel.setVisible(false);
                        }
                        break;
                    }
                }
            }
        });

        pack();
        setVisible(true);
    }
}

class RecruiterListPanel extends JPanel{
    JScrollPane jScrollPane;
    JPanel list;

    RecruiterListPanel(Company company){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        list = new JPanel();
        list.setLayout(new GridBagLayout());

        for(Recruiter recruiter : company.getRecruiters()){
            RecruiterPanel recruiterPanel = new RecruiterPanel();
            recruiterPanel.setPanel(recruiter);
            list.add(recruiterPanel, gbc);
        }

        jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension( 700, 400));
        jScrollPane.setLayout(new ScrollPaneLayout());

        add(jScrollPane);
    }
}

class RecruiterPanel extends JPanel{
    JLabel rating;
    JLabel name;
    JLabel salary;
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea i;

    RecruiterPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        rating = new JLabel("Rating :");
        name = new JLabel("Name :");
        salary = new JLabel("Salary :");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        i = new JTextArea();
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        i.setEditable(false);
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        i.setOpaque(false);

        add(name);
        add(t1, gbc);
        add(rating);
        add(t2, gbc);
        add(salary);
        add(t3, gbc);
        add(i, gbc);
    }

    public void setPanel(Recruiter recruiter){
        t1.setText(recruiter.getResume().information.getName());
        t2.setText(String.valueOf(recruiter.getRating()));
        t3.setText(String.valueOf(recruiter.getSalary()));
    }

    public void setPanel(){
        t1.setText("");
        t2.setText("");
        t3.setText("");
        setVisible(false);
    }
}

class ManagerPanel extends JPanel{
    JScrollPane jScrollPane;
    JPanel list;

    ManagerPanel(Manager manager){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        list = new JPanel();
        list.setLayout(new GridBagLayout());

        for(Request request : manager.getRequests()){
            RequestPanel requestPanel = new RequestPanel();
            requestPanel.setPanel(request, manager);
            list.add(requestPanel, gbc);
        }

        jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension( 700, 400));
        jScrollPane.setLayout(new ScrollPaneLayout());

        add(jScrollPane);
    }
}

class RequestPanel extends JPanel {
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea t4;
    JTextArea i;
    JButton button1;
    JButton button2;

    RequestPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        l1 = new JLabel("Username :");
        l2 = new JLabel("Apply for job :");
        l3 = new JLabel("Evaluated by :");
        l4 = new JLabel("Grade :");

        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t4 = new JTextArea();
        i = new JTextArea(2, 0);
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        i.setEditable(false);
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        t4.setOpaque(false);
        i.setOpaque(false);

        button1 = new JButton("Accept");
        button2 = new JButton("Refuse");

        add(l1);
        add(t1, gbc);
        add(l2);
        add(t2, gbc);
        add(l3);
        add(t3, gbc);
        add(l4);
        add(t4, gbc);
        add(button1);
        add(button2, gbc);
        add(i, gbc);
    }

    public void setPanel(Request request, Manager manager){
        t1.setText(((User)request.getValue1()).getResume().information.getName());
        t2.setText(((Job)request.getKey()).getJobName());
        t3.setText(((Recruiter)request.getValue2()).getResume().information.getName());
        t4.setText(String.valueOf(request.getScore()));

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(((Job)request.getKey()).getNumberCandidatesNeeded() == 0) {
                    JOptionPane.showMessageDialog(null, "Error : There are no more available" +
                            " positions for the job \"" + ((Job)request.getKey()).getJobName() + "\" !");
                    ((Job) request.getKey()).removeCandidate((User) request.getValue1());
                    manager.deleteRequest(request);
                    setVisible(false);
                } else {
                    manager.process((Job) request.getKey(), (User) request.getValue1());
                    ((Job) request.getKey()).removeCandidate((User) request.getValue1());
                    manager.deleteRequest(request);
                    setVisible(false);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((Job)request.getKey()).removeCandidate((User)request.getValue1());
                manager.deleteRequest(request);
                Application.getInstance().getCompany(manager.getCompanyName()).notifyAllObservers("User " +
                        ((User)request.getValue1()).getResume().information.getName() + " has been refused for the" +
                        " job \"" + ((Job)request.getKey()).getJobName() + " \" in company " + manager.getCompanyName() +
                        " by it's manager !");
                setVisible(false);
            }
        });
    }

    public void setPanel(){
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        setVisible(false);
    }
}

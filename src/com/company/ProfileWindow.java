package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfileWindow extends JFrame{
    JTextField jTextField_username ;
    JPasswordField jTextField_password ;
    JLabel jLabel_username;
    JLabel jLabel_password;
    JButton jButton;
    JButton jButton1;
    JButton jButton_info;
    JButton jButton_educ;
    JButton jButton_exp;
    JPanel username;
    JPanel information;
    JPanel about;
    JPanel notifications;
    JPanel jobs;
    JTextArea invisible;
    JTextArea invisible1;
    JTextArea invisible2;
    JTextArea i;
    JTextArea notif;
    JTabbedPane jTabbedPane;
    JobListPanel jobListPanel;
    User u = new User();
    JButton refresh;
    JTextArea no_jobs;
    JTextArea no_notif;

    ProfileWindow(){
        super("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        setLocationRelativeTo(null);

        username = new JPanel();
        information = new JPanel();
        about = new JPanel();
        notifications = new JPanel();
        jobs = new JPanel();
        information.setVisible(false);
        about.setVisible(false);
        notifications.setVisible(false);
        jobs.setVisible(false);
        add(username);
        add(about);

        username.setLayout(new GridBagLayout());
        information.setLayout(new GridBagLayout());
        about.setLayout(new GridBagLayout());
        notifications.setLayout(new GridBagLayout());
        jobs.setLayout(new GridBagLayout());

        jTabbedPane = new JTabbedPane();
        jTabbedPane.setMinimumSize(new Dimension(700, 400));
        jTextField_username = new JTextField(15);
        jTextField_password = new JPasswordField(15);
        jLabel_username = new JLabel();
        jLabel_password = new JLabel();
        jButton = new JButton();
        jButton1 = new JButton();
        jButton_info = new JButton("Information");
        jButton_educ = new JButton("Education");
        jButton_exp = new JButton("Experience");
        notif = new JTextArea();
        notif.setEditable(false);
        notif.setOpaque(false);
        invisible = new JTextArea(3, 0);
        invisible1 = new JTextArea(3, 0);
        invisible2 = new JTextArea(3, 0);
        invisible.setEditable(false);
        invisible1.setEditable(false);
        invisible2.setEditable(false);
        invisible.setOpaque(false);
        invisible1.setOpaque(false);
        invisible2.setOpaque(false);
        no_jobs = new JTextArea("There are no jobs available !");
        no_jobs.setEditable(false);
        no_jobs.setOpaque(false);
        no_jobs.setVisible(false);
        no_notif = new JTextArea("There are no notifications !");
        no_notif.setEditable(false);
        no_notif.setOpaque(false);
        no_notif.setVisible(false);

        about.add(jTabbedPane, gbc);
        information.add(jButton_info);
        information.add(jButton_educ);
        information.add(jButton_exp, gbc);
        information.add(invisible2, gbc);

        InfoPanel infoPanel = new InfoPanel();
        infoPanel.setVisible(false);
        information.add(infoPanel, gbc);
        EducationPanel educationPanel = new EducationPanel();
        educationPanel.setVisible(false);
        information.add(educationPanel, gbc);
        ExperiencePanel experiencePanel = new ExperiencePanel();
        experiencePanel.setVisible(false);
        information.add(experiencePanel, gbc);

        notifications.add(notif, gbc);
        notifications.add(no_notif, gbc);

        jTabbedPane.add("About", information);
        jTabbedPane.add("Notifications", notifications);
        jTabbedPane.add("Jobs", jobs);


        refresh = new JButton("Refresh");

        about.add(refresh, gbc);

        jButton_info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoPanel.setVisible(true);
                educationPanel.setVisible(false);
                experiencePanel.setVisible(false);
            }
        });
        jButton_educ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoPanel.setVisible(false);
                educationPanel.setVisible(true);
                experiencePanel.setVisible(false);
            }
        });
        jButton_exp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoPanel.setVisible(false);
                educationPanel.setVisible(false);
                experiencePanel.setVisible(true);
            }
        });


        jLabel_username.setText("Username :");
        jLabel_password.setText("Password :");
        username.add(jLabel_username);
        username.add(jTextField_username, gbc);
        username.add(jLabel_password);
        username.add(jTextField_password, gbc);
        i = new JTextArea(2,0);
        i.setEditable(false);
        i.setOpaque(false);
        username.add(i, gbc);

        jButton1.setText("Log out");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                information.setVisible(false);
                about.setVisible(false);
                notifications.setVisible(false);
                jobs.setVisible(false);
                jobs.removeAll();
                educationPanel.setEducation();
                experiencePanel.setExperience();
                infoPanel.setVisible(false);
                educationPanel.setVisible(false);
                experiencePanel.setVisible(false);
                username.setVisible(true);
                u = null;
            }
        });

        information.add(invisible1, gbc);
        information.add(jButton1, gbc);

        jButton.setText("Log in");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                if(!flag) {
                    for (User user : Application.getInstance().getUsers()) {
                        if (user.getResume().information.getName().equals(jTextField_username.getText()) &&
                                user.getPassword().equals(jTextField_password.getText())) {
                            username.setVisible(false);
                            jTextField_username.setText("");
                            jTextField_password.setText("");
                            information.setVisible(true);
                            about.setVisible(true);

                            infoPanel.setInfo(user.getResume().information);
                            infoPanel.setVisible(true);
                            educationPanel.setEducation(user.getResume().educations);
                            experiencePanel.setExperience(user.getResume().experiences);
                            notif.setText(user.getNotifications());
                            if(user.getNotifications().isEmpty()){
                                notif.setVisible(false);
                                no_notif.setVisible(true);
                            } else {
                                notif.setVisible(true);
                                no_notif.setVisible(false);
                            }

                            jobListPanel = new JobListPanel(user);
                            jobs.add(jobListPanel);
                            jobs.add(no_jobs);
                            if(user.jobs.size() == 0){
                                jobListPanel.setVisible(false);
                                no_jobs.setVisible(true);
                            } else {
                                jobListPanel.setVisible(true);
                                no_jobs.setVisible(false);
                            }

                            u = user;

                            flag = true;
                        }
                    }
                }
                if(!flag){
                    for(Company company : Application.getInstance().getCompanies()){
                        if(company.getManager().getResume().information.getName().equals(jTextField_username.getText())
                                && company.getManager().getPassword().equals(jTextField_password.getText())) {
                            username.setVisible(false);
                            jTextField_username.setText("");
                            jTextField_password.setText("");

                            ManagerWindow managerWindow = new ManagerWindow(company.getManager());
                            setVisible(false);

                            flag = true;
                        }
                    }
                }
                if(!flag) {
                    if(jTextField_username.getText().equals("Admin") && jTextField_password.getText().equals("admin")){
                        username.setVisible(false);
                        jTextField_username.setText("");
                        jTextField_password.setText("");

                        AdminWindow adminWindow = new AdminWindow();
                        setVisible(false);

                        flag = true;
                    }
                }
                if(!flag){
                    JOptionPane.showMessageDialog(null, "Your login credentials don't match" +
                            " an account in our system.");
                    jTextField_username.setText("");
                    jTextField_password.setText("");
                }
            }
        });
        username.add(jButton, gbc);

        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notif.setText(u.getNotifications());
                jobs.removeAll();
                jobListPanel = new JobListPanel(u);
                jobs.add(jobListPanel);
                jobs.add(no_jobs);
                if(u.getNotifications().isEmpty()){
                    notif.setVisible(false);
                    no_notif.setVisible(true);
                } else {
                    notif.setVisible(true);
                    no_notif.setVisible(false);
                }
                if(u.jobs.size() == 0){
                    jobListPanel.setVisible(false);
                    no_jobs.setVisible(true);
                } else {
                    jobListPanel.setVisible(true);
                    no_jobs.setVisible(false);
                }
            }
        });

        pack();
        setVisible(true);
    }
}

class InfoPanel extends JPanel {
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea t4;
    JTextArea t5;
    JTextArea t6;
    JTextArea t7;
    InfoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JLabel l1 = new JLabel("Name : ");
        JLabel l2 = new JLabel("Email : ");
        JLabel l3 = new JLabel("Phone : ");
        JLabel l4 = new JLabel("Date of birth : ");
        JLabel l5 = new JLabel("Genre : ");
        JLabel l6 = new JLabel("Languages : ");
        JLabel l7 = new JLabel("Languages level : ");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t4 = new JTextArea();
        t5 = new JTextArea();
        t6 = new JTextArea();
        t7 = new JTextArea();
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(false);
        t7.setEditable(false);
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        t4.setOpaque(false);
        t5.setOpaque(false);
        t6.setOpaque(false);
        t7.setOpaque(false);
        add(l1);
        add(t1, gbc);
        add(l2);
        add(t2, gbc);
        add(l3);
        add(t3, gbc);
        add(l4);
        add(t4, gbc);
        add(l5);
        add(t5, gbc);
        add(l6);
        add(t6, gbc);
        add(l7);
        add(t7, gbc);
    }

    public void setInfo(Information i){
        t1.setText(i.getName());
        t2.setText(i.getEmail());
        t3.setText(i.getPhone());
        t4.setText(i.getDate_of_birth());
        t5.setText(i.getGenre());
        t6.setText(i.getLanguages().toString());
        t7.setText(i.getLanguages_level().toString());
    }
}

class EducationPanel extends JPanel{
    JComboBox<String> jComboBox;
    JPanel jPanel;
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea t4;
    JTextArea t5;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;

    EducationPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jComboBox = new JComboBox<>();
        add(jComboBox, gbc);

        l1 = new JLabel("Level : ");
        l2 = new JLabel("Name : ");
        l3 = new JLabel("Start date : ");
        l4 = new JLabel("End date : ");
        l5 = new JLabel("Grade : ");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t4 = new JTextArea();
        t5 = new JTextArea();
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        t4.setOpaque(false);
        t5.setOpaque(false);

        jPanel.add(l1);
        jPanel.add(t1, gbc);
        jPanel.add(l2);
        jPanel.add(t2, gbc);
        jPanel.add(l3);
        jPanel.add(t3, gbc);
        jPanel.add(l4);
        jPanel.add(t4, gbc);
        jPanel.add(l5);
        jPanel.add(t5, gbc);
        add(jPanel, gbc);
    }

    public void setEducation(ArrayList<Education> educations){
        for(Education e : educations) {
            jComboBox.addItem(e.getLevel());
        }
        Education ed = educations.get(0);
        t1.setText(ed.getLevel());
        t2.setText(ed.getName());
        t3.setText(ed.getStart_date());
        t4.setText(ed.getEnd_date());
        t5.setText(String.valueOf(ed.getGrade()));

        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                for(Education ed : educations) {
                    if (ed.getLevel().equals(jComboBox.getSelectedItem())) {
                        t1.setText(ed.getLevel());
                        t2.setText(ed.getName());
                        t3.setText(ed.getStart_date());
                        t4.setText(ed.getEnd_date());
                        t5.setText(String.valueOf(ed.getGrade()));
                        break;
                    }
                }
            }
        });
    }

    public void setEducation(){
        jComboBox.removeAllItems();
    }
}

class ExperiencePanel extends JPanel{
    JComboBox<String> jComboBox;
    JPanel jPanel;
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea t4;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;

    ExperiencePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jComboBox = new JComboBox<>();
        add(jComboBox, gbc);

        l1 = new JLabel("Company : ");
        l2 = new JLabel("Position : ");
        l3 = new JLabel("Start date : ");
        l4 = new JLabel("End date : ");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t4 = new JTextArea();
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        t4.setOpaque(false);

        jPanel.add(l1);
        jPanel.add(t1, gbc);
        jPanel.add(l2);
        jPanel.add(t2, gbc);
        jPanel.add(l3);
        jPanel.add(t3, gbc);
        jPanel.add(l4);
        jPanel.add(t4, gbc);
        add(jPanel, gbc);
    }

    public void setExperience(ArrayList<Experience> experiences){
        for(Experience e : experiences) {
            jComboBox.addItem(e.getCompany());
        }
        Experience ex = experiences.get(0);
        t1.setText(ex.getCompany());
        t2.setText(ex.getPosition());
        t3.setText(ex.getStart_date());
        t4.setText(ex.getEnd_date());

        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                for(Experience ex : experiences) {
                    if (ex.getCompany().equals(jComboBox.getSelectedItem())) {
                        t1.setText(ex.getCompany());
                        t2.setText(ex.getPosition());
                        t3.setText(ex.getStart_date());
                        t4.setText(ex.getEnd_date());
                        break;
                    }
                }
            }
        });
    }

    public void setExperience(){
        jComboBox.removeAllItems();
    }
}

class JobListPanel extends JPanel{
    JScrollPane jScrollPane;
    JPanel list;

    JobListPanel(User user){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        list = new JPanel();
        list.setLayout(new GridBagLayout());

        for(Job job : user.jobs) {
            JobPanel jobPanel = new JobPanel();
            jobPanel.setPanel(job, user);
            list.add(jobPanel, gbc);
        }

        jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension( 700, 400));
        jScrollPane.setLayout(new ScrollPaneLayout());

        add(jScrollPane);
    }
}

class JobPanel extends JPanel{
    JTextArea t1;
    JTextArea t2;
    JTextArea t3;
    JTextArea i;
    JTextArea i1;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JButton jButton;

    JobPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        setLayout(new GridBagLayout());

        l1 = new JLabel("Company : ");
        l2 = new JLabel("Job name : ");
        l3 = new JLabel("Salary : ");
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        i = new JTextArea(2,0);
        i1 = new JTextArea(2,0);
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        i.setEditable(false);
        i1.setEditable(false);
        t1.setOpaque(false);
        t2.setOpaque(false);
        t3.setOpaque(false);
        i.setOpaque(false);
        i1.setOpaque(false);
        jButton = new JButton("Apply");

        add(l1);
        add(t1, gbc);
        add(l2);
        add(t2, gbc);
        add(l3);
        add(t3, gbc);
        add(i1, gbc);
        add(jButton, gbc);
        add(i, gbc);
    }

    public void setPanel(Job job, User user){
        t1.setText(job.getCompany());
        t2.setText(job.getJobName());
        t3.setText(String.valueOf(job.getSalary()));

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(user.jobs.contains(job)) {
                    job.apply(user);
                    JOptionPane.showMessageDialog(null, "Your apply was processed successfully !"
                            + " You will receive a notification with the decision of the company manager. Good luck !");
                    user.jobs.remove(job);
                } else {
                    JOptionPane.showMessageDialog(null, "You already applied for this job !");
                }
            }
        });
    }
}


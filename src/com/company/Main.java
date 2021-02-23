package com.company;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("./consumers.json"));
        GsonType gsonType = gson.fromJson(reader, GsonType.class);
        reader.close();

        //----------------------------------------------Lists-----------------------------------------------------------

        ArrayList<Employee> employees = new ArrayList<>();
        for(GsonConsumer gsonConsumer : gsonType.employees){
            Information information = new Information();
            information.setName(gsonConsumer.name);
            information.setEmail(gsonConsumer.email);
            information.setPhone(gsonConsumer.phone);
            information.setDate_of_birth(gsonConsumer.date_of_birth);
            information.setGenre(gsonConsumer.genre);
            information.setLanguages(gsonConsumer.languages);
            information.setLanguages_level(gsonConsumer.languages_level);

            ArrayList<Education> educations = new ArrayList<>();
            educations.addAll(gsonConsumer.education);
            for(Education e : educations){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            ArrayList<Experience> experiences = new ArrayList<>();
            experiences.addAll(gsonConsumer.experience);
            for(Experience e : experiences){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            Consumer.Resume.Builder builder = new Consumer.Resume.Builder(information, educations);
            builder.experience(experiences);

            Consumer.Resume resume = new Consumer.Resume(builder);
            String jobName = new String();
            for(Experience experience : gsonConsumer.experience){
                if(experience.getEnd_date() == null){
                    jobName = experience.getPosition();
                    break;
                }
            }
            Employee employee = new Employee(gsonConsumer.experience.get(0).getCompany(), gsonConsumer.salary,
                    jobName, resume);
            employee.setSalary(gsonConsumer.salary);
            for(Experience experience : employee.getResume().experiences) {
                if (experience.getEnd_date_dateType() == null) {
                    employee.setJobName(experience.getPosition());
                    break;
                }
            }
            employees.add(employee);
        }
        for(int i = 0; i < 5; i++)
        {
            employees.get(i).setCompanyName("Amazon");
        }

        for(int i = 5; i < 10; i++)
        {
            employees.get(i).setCompanyName("Google");
        }

        ArrayList<Recruiter> recruiters = new ArrayList<>();
        for(GsonConsumer gsonConsumer : gsonType.recruiters){
            Information information = new Information();
            information.setName(gsonConsumer.name);
            information.setEmail(gsonConsumer.email);
            information.setPhone(gsonConsumer.phone);
            information.setDate_of_birth(gsonConsumer.date_of_birth);
            information.setGenre(gsonConsumer.genre);
            information.setLanguages(gsonConsumer.languages);
            information.setLanguages_level(gsonConsumer.languages_level);

            ArrayList<Education> educations = new ArrayList<>();
            educations.addAll(gsonConsumer.education);
            for(Education e : educations){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            ArrayList<Experience> experiences = new ArrayList<>();
            experiences.addAll(gsonConsumer.experience);
            for(Experience e : experiences){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            Consumer.Resume.Builder builder = new Consumer.Resume.Builder(information, educations);
            builder.experience(experiences);

            Consumer.Resume resume = new Consumer.Resume(builder);

            Recruiter recruiter = new Recruiter(gsonConsumer.experience.get(0).getCompany(), gsonConsumer.salary,
                    gsonConsumer.experience.get(0).getPosition(), resume);
            recruiter.setSalary(gsonConsumer.salary);
            recruiters.add(recruiter);
        }

        ArrayList<User> users = new ArrayList<>();
        for(GsonConsumer gsonConsumer : gsonType.users){
            Information information = new Information();
            information.setName(gsonConsumer.name);
            information.setEmail(gsonConsumer.email);
            information.setPhone(gsonConsumer.phone);
            information.setDate_of_birth(gsonConsumer.date_of_birth);
            information.setGenre(gsonConsumer.genre);
            information.setLanguages(gsonConsumer.languages);
            information.setLanguages_level(gsonConsumer.languages_level);

            ArrayList<Education> educations = new ArrayList<>();
            educations.addAll(gsonConsumer.education);
            for(Education e : educations){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            ArrayList<Experience> experiences = new ArrayList<>();
            experiences.addAll(gsonConsumer.experience);
            for(Experience e : experiences){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            Consumer.Resume.Builder builder = new Consumer.Resume.Builder(information, educations);
            builder.experience(experiences);

            Consumer.Resume resume = new Consumer.Resume(builder);
            User user = new User(resume);
            user.setInterested_companies(gsonConsumer.interested_companies);
            users.add(user);
            Application.getInstance().addUser(user);
        }

        ArrayList<Manager> managers = new ArrayList<>();
        for(GsonConsumer gsonConsumer : gsonType.managers){
            Information information = new Information();
            information.setName(gsonConsumer.name);
            information.setEmail(gsonConsumer.email);
            information.setPhone(gsonConsumer.phone);
            information.setDate_of_birth(gsonConsumer.date_of_birth);
            information.setGenre(gsonConsumer.genre);
            information.setLanguages(gsonConsumer.languages);
            information.setLanguages_level(gsonConsumer.languages_level);

            ArrayList<Education> educations = new ArrayList<>();
            educations.addAll(gsonConsumer.education);
            for(Education e : educations){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            ArrayList<Experience> experiences = new ArrayList<>();
            experiences.addAll(gsonConsumer.experience);
            for(Experience e : experiences){
                e.setStart_date(e.getStart_date());
                e.setEnd_date(e.getEnd_date());
            }

            Consumer.Resume.Builder builder = new Consumer.Resume.Builder(information, educations);
            builder.experience(experiences);

            Consumer.Resume resume = new Consumer.Resume(builder);

            Manager manager = new Manager(gsonConsumer.experience.get(0).getCompany(), gsonConsumer.salary,
                    gsonConsumer.experience.get(0).getPosition(), resume);
            manager.setSalary(gsonConsumer.salary);
            managers.add(manager);
        }

        reader = Files.newBufferedReader(Paths.get("./jobs.json"));
        GsonType2 gsonType2 = gson.fromJson(reader, GsonType2.class);
        reader.close();

        ArrayList<Job> jobs = new ArrayList<>();
        for(GsonJob gsonJob : gsonType2.jobs){
            Job job = new Job(gsonJob.jobName, gsonJob.companyName, true, new Constraint(gsonJob.finalYear_min,
                    gsonJob.finalYear_max), new Constraint(gsonJob.experienceYears_min, gsonJob.experienceYears_max),
                    new Constraint(gsonJob.meanGPA_min, gsonJob.meanGPA_max), new ArrayList<User>(),
                    gsonJob.numberCandidatesNeeded, gsonJob.salary);
            jobs.add(job);
        }

        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------Tree--------------------------------------------------------

        users.get(0).add(users.get(1));
        users.get(0).add(employees.get(2));

        users.get(1).add(users.get(0));
        users.get(1).add(recruiters.get(0));
        users.get(1).add(employees.get(6));

        users.get(2).add(users.get(3));
        users.get(2).add(employees.get(2));

        users.get(3).add(users.get(2));
        users.get(3).add(employees.get(9));

        employees.get(1).add(employees.get(9));
        employees.get(1).add(recruiters.get(2));

        employees.get(2).add(users.get(0));
        employees.get(2).add(users.get(2));
        employees.get(2).add(employees.get(5));
        employees.get(2).add(recruiters.get(1));

        employees.get(5).add(employees.get(2));
        employees.get(5).add(recruiters.get(3));

        employees.get(9).add(employees.get(1));
        employees.get(9).add(users.get(3));

        employees.get(6).add(users.get(1));

        recruiters.get(0).add(users.get(1));

        recruiters.get(1).add(employees.get(2));

        recruiters.get(2).add(employees.get(1));

        recruiters.get(3).add(employees.get(5));

        //--------------------------------------------------------------------------------------------------------------

        //---------------------------------------------Company----------------------------------------------------------

        ArrayList<Recruiter> amazonRecruiters = new ArrayList<>();
        amazonRecruiters.add(recruiters.get(2));
        amazonRecruiters.add(recruiters.get(3));
        ArrayList<Department> amazonDepartments = new ArrayList<>();
        ArrayList<Employee> employees1 = new ArrayList<>();
        ArrayList<Job> jobs1 = new ArrayList<>();
        employees1.add(employees.get(0));
        employees1.addAll(amazonRecruiters);
        jobs1.add(jobs.get(2));
        jobs1.add(jobs.get(3));
        Department it = new IT(employees1, jobs1);
        amazonDepartments.add(it);
        ArrayList<Employee> employees2 = new ArrayList<>();
        employees2.add(employees.get(1));
        Department management = new Management(employees2, new ArrayList<Job>());
        amazonDepartments.add(management);
        ArrayList<Employee> employees3 = new ArrayList<>();
        employees3.add(employees.get(2));
        employees3.add(employees.get(4));
        Department marketing = new Marketing(employees3, new ArrayList<Job>());
        amazonDepartments.add(marketing);
        ArrayList<Employee> employees4 = new ArrayList<>();
        employees4.add(employees.get(3));
        Department finance = new Finance(employees4, new ArrayList<Job>());
        amazonDepartments.add(finance);

        ArrayList<Recruiter> googleRecruiters = new ArrayList<>();
        googleRecruiters.add(recruiters.get(0));
        googleRecruiters.add(recruiters.get(1));
        ArrayList<Department> googleDepartments = new ArrayList<>();
        ArrayList<Employee> employees5 = new ArrayList<>();
        ArrayList<Job> jobs2 = new ArrayList<>();
        employees5.add(employees.get(5));
        employees5.addAll(googleRecruiters);
        jobs2.add(jobs.get(0));
        jobs2.add(jobs.get(1));
        Department it1 = new IT(employees5, jobs2);
        googleDepartments.add(it1);
        ArrayList<Employee> employees6 = new ArrayList<>();
        employees6.add(employees.get(6));
        Department management1 = new Management(employees6, new ArrayList<Job>());
        googleDepartments.add(management1);
        ArrayList<Employee> employees7 = new ArrayList<>();
        employees7.add(employees.get(7));
        employees7.add(employees.get(9));
        Department marketing1 = new Marketing(employees7, new ArrayList<Job>());
        googleDepartments.add(marketing1);
        ArrayList<Employee> employees8 = new ArrayList<>();
        employees8.add(employees.get(8));
        Department finance1 = new Finance(employees8, new ArrayList<Job>());
        googleDepartments.add(finance1);

        Company Google = new Company("Google",  managers.get(0),
                googleDepartments, googleRecruiters);
        Company Amazon = new Company("Amazon", managers.get(1),
                amazonDepartments, amazonRecruiters);
        Application application = Application.getInstance();
        application.addCompany(Google);
        application.addCompany(Amazon);

        //--------------------------------------------------------------------------------------------------------------

        //-----------------------------------------Intereseted jobs-----------------------------------------------------
        for(User user : users){
            for(String interested : user.interested_companies){
                for(Job job : application.getCompany(interested).getJobs()) {
                    user.jobs.add(job);
                }
            }
        }
        //--------------------------------------------------------------------------------------------------------------

        ProfileWindow profileWindow = new ProfileWindow();
    }
}
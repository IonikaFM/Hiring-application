package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Manager extends Employee{
    private ArrayList<Request> requests;

    Manager() {
        super();
        requests = new ArrayList<>();
        setPassword("manager");
    }

    Manager(ArrayList<Request> requests){
        super();
        this.requests = requests;
        setPassword("manager");
    }

    Manager(String company, int salary, String jobName, Resume resume){
        super(company, salary, jobName, resume);
        requests = new ArrayList<>();
        setPassword("manager");
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void add(Request request){
        requests.add(request);
        Collections.sort(requests);
    }

    public void process(Job job){
        Application application = Application.getInstance();
        int number = job.getNumberCandidatesNeeded();
        for(Request request : requests){
            if(number == 0) {
                job.setFlag(false);
                break;
            }
            if(request.getKey().equals(job) && number > 0){
                for(User user: job.getCandidates()) {
                    if (application.getUsers().contains(user)) {
                        if (user.equals(request.getValue1())) {
                            number--;
                            for (Department department : application.getCompany(getCompanyName()).getDepartments()) {
                                for (Job jobs : department.getJobs()) {
                                    if (jobs.equals(job)) {
                                        department.add(user.convert());
                                        application.getCompany(getCompanyName()).notifyAllObservers("Userul "
                                                + user.getResume().information.getName() +
                                                " a fost angajat in compania " + getCompanyName());
                                        break;
                                    }
                                }
                            }
                            application.remove(user);
                            for (Company company : application.getCompanies()) {
                                company.removeObserver(user);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void process(Job job, User user){
        Application application = Application.getInstance();
        if(job.getNumberCandidatesNeeded() != 0) {
            if (application.getUsers().contains(user)) {
                job.setNumberCandidatesNeeded(job.getNumberCandidatesNeeded() - 1);
                for (Department department : application.getCompany(getCompanyName()).getDepartments()) {
                    for (Job jobs : department.getJobs()) {
                        if (jobs.equals(job)) {
                            Employee employee = user.convert();
                            employee.setSalary(job.getSalary());
                            employee.setJobName(job.getJobName());
                            department.add(employee);

                            application.getCompany(getCompanyName()).notifyAllObservers("Userul "
                                    + user.getResume().information.getName() +
                                    " a fost angajat in compania " + getCompanyName());
                            break;
                        }
                    }
                }
                application.remove(user);
                for (Company company : application.getCompanies()) {
                    company.removeObserver(user);
                }
            }
        }
        if(job.getNumberCandidatesNeeded() == 0){
            job.setFlag(false);
        }
    }

    public void deleteRequest(Request request){
        if(requests.contains(request)){
            requests.remove(request);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(requests, manager.requests);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), requests);
    }

    public String toString() {
        return "Manager{" +
                "requests=" + requests + "\n" + super.toString() +
                '}';
    }
}


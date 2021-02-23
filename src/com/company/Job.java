package com.company;

import javax.crypto.Cipher;
import javax.swing.*;
import java.util.ArrayList;

public class Job {
    private String jobName;
    private String companyName;
    private boolean flag;
    private Constraint finalYear;
    private Constraint experienceYears;
    private Constraint meanGPA;
    private ArrayList<User> candidates;
    private int numberCandidatesNeeded;
    private double salary;

    Job() {
        jobName = new String("");
        companyName = new String("");
        flag = true;
        finalYear = new Constraint();
        experienceYears = new Constraint();
        meanGPA = new Constraint();
        candidates = new ArrayList<>();
        numberCandidatesNeeded = 0;
        salary = 0;
    }

    Job(String jobName, String companyName, boolean flag, Constraint finalYear, Constraint experienceYears, Constraint
        meanGPA, ArrayList<User> candidates, int numberCandidatesNeeded, double salary) {
        this.jobName = jobName;
        this.companyName = companyName;
        this.flag = flag;
        this.finalYear = finalYear;
        this.experienceYears = experienceYears;
        this.meanGPA = meanGPA;
        this.candidates = candidates;
        this.numberCandidatesNeeded = numberCandidatesNeeded;
        this.salary = salary;

        if(this.finalYear.getSuperiorLimit() == (Object) 0)
            this.finalYear.setSuperiorLimit(2050);

        if(this.experienceYears.getSuperiorLimit() == (Object) 0)
            this.experienceYears.setSuperiorLimit(2050);

        this.meanGPA.setSuperiorLimit(10.1);
    }

    public String getJobName() {
        return jobName;
    }

    public ArrayList<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<User> candidates) {
        this.candidates = candidates;
    }

    public int getNumberCandidatesNeeded() {
        return numberCandidatesNeeded;
    }

    public void setNumberCandidatesNeeded(int numberCandidatesNeeded) {
        this.numberCandidatesNeeded = numberCandidatesNeeded;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompany() {
        return companyName;
    }

    public void setCompany(String company) {
        this.companyName = company;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        if(flag == false){
            Company company = Application.getInstance().getCompany(companyName);
            company.notifyAllObservers("A job from company " + companyName +
                    " that you are following is no longer active!");
            for(Request request : company.getManager().getRequests()){
                if(request.getKey().equals(jobName)){
                    company.getManager().deleteRequest(request);
                }
            }
            candidates.removeAll(candidates);
            for(Department department : company.getDepartments()){
                if(department.getJobs().contains(this)){
                    department.remove(this);
                }
            }
        }
    }

    public Constraint getFinalYear() {
        return finalYear;
    }

    public void setFinalYear(Constraint finalYear) {
        this.finalYear = finalYear;
    }

    public Constraint getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Constraint experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Constraint getMeanGPA() {
        return meanGPA;
    }

    public void setMeanGPA(Constraint meanGPA) {
        this.meanGPA = meanGPA;
    }

    public boolean meetsRequirments(User user) {
        int year = user.getGraduationYear();
        if(year < (int)(finalYear.getInferiorLimit()) || year > (int)(finalYear.getSuperiorLimit())) { return false; }
        if(user.getExperienceYears() < (int)(experienceYears.getInferiorLimit()) ||
                user.getExperienceYears() > (int)(experienceYears.getSuperiorLimit())) { return false; }
        if(user.meanGPA() < (double)(meanGPA.getInferiorLimit()) ||
                user.meanGPA() > (double)(meanGPA.getSuperiorLimit())) { return false; }
        return true;
    }

    public void apply(User user){
        Application.getInstance().getCompany(companyName).addObserver(user);
        Application.getInstance().getCompany(companyName).getRecruiter(user).evaluate(this, user);
    }

    public void addCandidate(User user){
        candidates.add(user);
    }

    public void removeCandidate(User user){
        if(candidates.contains(user)){
            candidates.remove(user);
        }
    }

    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", flag=" + flag +
                ", finalYear=" + finalYear +
                ", experienceYears=" + experienceYears +
                ", meanGPA=" + meanGPA +
                ", candidates=" + candidates +
                ", numberCandidatesNeeded=" + numberCandidatesNeeded +
                ", salary=" + salary +
                "}\n";
    }
}

package com.company;

import java.util.Date;
import java.util.Objects;

public class Education implements Comparable{
    private String start_date;
    private String end_date;
    private Date start_date_dateType;
    private Date end_date_dateType;
    private String name;
    private String level;
    private double grade;

    Education() {
        start_date = new String();
        end_date = new String();
        start_date_dateType = null;
        end_date_dateType = null;
        name = new String();
        level = new String();
        grade = 0;
    }

    Education(String start_date, String end_date, String name, String level, double grade){
        try {
            this.start_date = start_date;
            if(start_date != null) {
                String[] string = start_date.split("\\.");
                start_date_dateType = new Date();
                start_date_dateType.setDate(Integer.parseInt(string[0]));
                start_date_dateType.setMonth(Integer.parseInt(string[1]));
                start_date_dateType.setYear(Integer.parseInt(string[2]));
            } else { start_date_dateType = null; }
            if(end_date != null) {
                String[] string = end_date.split("\\.");
                end_date_dateType = new Date();
                end_date_dateType.setDate(Integer.parseInt(string[0]));
                end_date_dateType.setMonth(Integer.parseInt(string[1]));
                end_date_dateType.setYear(Integer.parseInt(string[2]));
            } else { end_date_dateType = null; }
            this.end_date = end_date;
            this.name = name;
            this.level = level;
            this.grade = grade;
            if(end_date_dateType != null)
                if(start_date_dateType.after(end_date_dateType))
                    throw new InvalidDatesException();
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        }
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
        start_date_dateType = new Date();
        if(start_date != null) {
            String[] string = start_date.split("\\.");
            start_date_dateType = new Date();
            start_date_dateType.setDate(Integer.parseInt(string[0]));
            start_date_dateType.setMonth(Integer.parseInt(string[1]));
            start_date_dateType.setYear(Integer.parseInt(string[2]));
        } else { start_date_dateType = null; }
    }

    public String getEnd_date() { return end_date; }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
        end_date_dateType = new Date();
        if(end_date != null) {
            String[] string = end_date.split("\\.");
            end_date_dateType = new Date();
            end_date_dateType.setDate(Integer.parseInt(string[0]));
            end_date_dateType.setMonth(Integer.parseInt(string[1]));
            end_date_dateType.setYear(Integer.parseInt(string[2]));
        } else { end_date_dateType = null; }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name   = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getStart_date_dateType() {
        return start_date_dateType;
    }

    public void setStart_date_dateType(Date start_date_dateType) {
        this.start_date_dateType = start_date_dateType;
    }

    public Date getEnd_date_dateType() {
        return end_date_dateType;
    }

    public void setEnd_date_dateType(Date end_date_dateType) {
        this.end_date_dateType = end_date_dateType;
    }

    public int compareTo(Object o) {
        int i = 1;
        Education education = (Education) o;
        if (education.getEnd_date_dateType() == null || end_date_dateType == null) {
            i =  start_date_dateType.compareTo(education.getStart_date_dateType());
        } else if (education.getEnd_date_dateType().equals(end_date_dateType)) {
            if (education.getGrade() > grade)
                i = 1;
            else
                i =  -1;
        } else {
            i = education.getEnd_date_dateType().compareTo(end_date_dateType);
        }
        return i;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Education)) return false;
        Education education = (Education) o;
        return Double.compare(education.grade, grade) == 0 && Objects.equals(start_date, education.start_date) && Objects.equals(end_date, education.end_date) && Objects.equals(start_date_dateType, education.start_date_dateType) && Objects.equals(end_date_dateType, education.end_date_dateType) && Objects.equals(name, education.name) && Objects.equals(level, education.level);
    }

    public int hashCode() {
        return Objects.hash(start_date, end_date, start_date_dateType, end_date_dateType, name, level, grade);
    }

    public String toString() {
        if(end_date != null)
            return "start_date = " + start_date +
                    "\n" + "end_date = " + end_date + "\n" + "name = " + name + "\n" + "level = "
                    + level + "\n" + "grade = " + grade + "\n";
        else
            return "start_date = " + start_date +
                    "\n" + "end_date = " + end_date + " -" + "\n" + "name = " + name + "\n" +
                    "level = " + level + "\n" + "grade = " + grade + "\n";
    }
}

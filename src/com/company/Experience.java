package com.company;

import java.util.Date;
import java.util.Objects;

public class Experience implements Comparable{
    private String start_date;
    private String end_date;
    private Date start_date_dateType;
    private Date end_date_dateType;
    private String position;
    private String company;

    Experience(){
        start_date = new String();
        end_date = new String();
        start_date_dateType = null;
        end_date_dateType = null;
        position = new String();
        company = new String();
    }

    Experience(String start_date, String end_date, String position, String company){
        try{
            this.start_date = start_date;
            if(start_date != null) {
                String[] string = start_date.split("\\.");
                start_date_dateType = new Date();
                start_date_dateType.setDate(Integer.parseInt(string[0]));
                start_date_dateType.setMonth(Integer.parseInt(string[1]));
                start_date_dateType.setYear(Integer.parseInt(string[2]));
            }
            if(end_date != null) {
                String[] string = end_date.split("\\.");
                end_date_dateType = new Date();
                end_date_dateType.setDate(Integer.parseInt(string[0]));
                end_date_dateType.setMonth(Integer.parseInt(string[1]));
                end_date_dateType.setYear(Integer.parseInt(string[2]));
            }
            this.end_date = end_date;
            this.position = position;
            this.company = company;
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
        }
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
        end_date_dateType = new Date();
        if(end_date != null) {
            String[] string = end_date.split("\\.");
            end_date_dateType = new Date();
            end_date_dateType.setDate(Integer.parseInt(string[0]));
            end_date_dateType.setMonth(Integer.parseInt(string[1]));
            end_date_dateType.setYear(Integer.parseInt(string[2]));
        }
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
        int i = 0;
        try {
            Experience experience = (Experience) o;
            if(experience.getEnd_date_dateType() == null || end_date_dateType == null ||
                    experience.getEnd_date_dateType().compareTo(end_date_dateType) == 0) {
                i = company.compareTo(experience.getCompany());
            }
            else {
                i = experience.getEnd_date_dateType().compareTo(end_date_dateType);
            }
            if(end_date_dateType != null)
                if(start_date_dateType.after(end_date_dateType))
                    throw new InvalidDatesException();
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;
        Experience that = (Experience) o;
        return Objects.equals(start_date, that.start_date) && Objects.equals(end_date, that.end_date) && Objects.equals(start_date_dateType, that.start_date_dateType) && Objects.equals(end_date_dateType, that.end_date_dateType) && Objects.equals(position, that.position) && Objects.equals(company, that.company);
    }

    public int hashCode() {
        return Objects.hash(start_date, end_date, start_date_dateType, end_date_dateType, position, company);
    }

    public String toString() {
        if(end_date != null)
            return "start_date = " + start_date +
                    "\n" + "end_date = " + end_date + "\n" + "company = " + company + "\n" + "position = "
                    + position + "\n";
        else
            return "start_date = " + start_date +
                    "\n" + "end_date = " + end_date + " -" + "\n" + "company = " + company + "\n" + "position = "
                    + position + "\n";
    }
}

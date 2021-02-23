package com.company;

import java.awt.event.ActionEvent;
import java.util.*;

public class Information {
    private String name;
    private String email;
    private String phone;
    private String date_of_birth;
    private Date date_of_birth_dateType;
    private String genre;
    private ArrayList<String> languages;
    private ArrayList<String> languages_level;

    public Information() {
        name = new String();
        email = new String();
        phone = new String();
        date_of_birth = new String();
        date_of_birth_dateType = new Date();
        genre = new String();
        languages = new ArrayList<>();
        languages_level = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getLanguages_level() { return languages_level; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
        String[] string = date_of_birth.split("\\.");
        date_of_birth_dateType.setDate(Integer.parseInt(string[0]));
        date_of_birth_dateType.setMonth(Integer.parseInt(string[1]));
        date_of_birth_dateType.setYear(Integer.parseInt(string[2]));
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public void setLanguages_level(ArrayList<String> languages_level) {
        this.languages_level = languages_level;
    }

    public void addLanguage(String language, String level) {
        this.languages.add(language);
        languages_level.add(level);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Information)) return false;
        Information that = (Information) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(date_of_birth, that.date_of_birth) && Objects.equals(date_of_birth_dateType, that.date_of_birth_dateType) && Objects.equals(genre, that.genre) && Objects.equals(languages, that.languages) && Objects.equals(languages_level, that.languages_level);
    }

    public int hashCode() {
        return Objects.hash(name, email, phone, date_of_birth, date_of_birth_dateType, genre, languages, languages_level);
    }

    public String toString() {

        return "name = " + name + "\n" +
                "email = " + email + "\n" +
                "phone = " + phone + "\n" +
                "date_of_birth = " + date_of_birth + "\n" + "genre = " + genre + "\n" + "languages = " +
                languages + "\n" + "languages_level = " + languages_level;
    }
}

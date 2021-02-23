package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.net.ConnectException;
import java.util.*;

abstract public class Consumer {
    private LinkedList<Consumer> consumers;
    private Resume resume;
    private String password;

    Consumer() {
        consumers = new LinkedList<>();
        resume = new Resume();
        password = new String();
    }

    Consumer(Resume resume) {
        this.consumers = new LinkedList<>();
        this.resume = resume;
        password = new String();
    }

    Consumer(Resume resume, LinkedList<Consumer> consumers) {
        this.consumers = new LinkedList<>(consumers);
        this.resume = resume;
        password = new String();
    }

    static class Resume {
        Information information;
        ArrayList<Education> educations;
        ArrayList<Experience> experiences;

        Resume(){
            information = new Information();
            educations = new ArrayList<>();
            experiences = new ArrayList<>();
        }

        Resume(Builder builder) {
            try {
                this.information = builder.information;
                this.educations = builder.educations;
                this.experiences = builder.experiences;
                if(information == null || educations.size() == 0) throw new ResumeIncompleteException();
            }
            catch (ResumeIncompleteException exception){
                exception.printStackTrace();
            }
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Resume)) return false;
            Resume resume = (Resume) o;
            return Objects.equals(information, resume.information) && Objects.equals(educations, resume.educations) && Objects.equals(experiences, resume.experiences);
        }

        public int hashCode() {
            return Objects.hash(information, educations, experiences);
        }

        public String toString () {
            return "Information : " + information + "\nEducations : " + educations + "\n\nExperiences : " + experiences;
        }

        public static class Builder {
            private Information information;
            private ArrayList<Education> educations;
            private ArrayList<Experience> experiences;

            public Builder(Information information, ArrayList<Education> educations){
                this.information = information;
                this.educations = educations;
            }

            public Builder experience(ArrayList<Experience> experience){
                this.experiences = experience;
                return this;
            }

            public Resume build(){
                return new Resume(this);
            }
        }
    }

    public Resume getResume() {
        return resume;
    }

    public LinkedList<Consumer> getConsumers() {
        return consumers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void add(Education education) {
        resume.educations.add(education);
        Collections.sort(resume.educations);
    }

    public void add(Experience experience) {
        resume.experiences.add(experience);
        Collections.sort(resume.experiences);
    }

    public void add(Consumer consumer) {
        if(!consumers.contains(consumer)) {
            consumers.add(consumer);
        }
    }

    public int getDegreeInFriendship(Consumer consumer) {
        HashMap<Consumer, Integer> hashMap = new HashMap<>();
        LinkedList<Consumer> queue = new LinkedList<>();
        Consumer c;

        for (Consumer consumerList1 : consumers) {
            if (!hashMap.containsKey(consumerList1)) {
                hashMap.put(consumerList1, 1);
                queue.add(consumerList1);

                while(queue.size() != 0){
                    c = queue.poll();
                    if(c.equals(consumer)) {
                        return hashMap.get(c);
                    }
                    for (Consumer consumerList2 : c.consumers) {
                        if (!hashMap.containsKey(consumerList2)) {
                            hashMap.put(consumerList2, hashMap.get(c) + 1);
                            queue.add(consumerList2);
                        }
                    }
                }
            }
        }

        return -1;
    }

    public void remove(Consumer consumer) {
        consumers.remove(consumer);
    }

    public int getGraduationYear() {
        for(Education education : resume.educations){
            if(education.getLevel().equals("college")) {
                if(education.getEnd_date_dateType() != null) {
                    return education.getEnd_date_dateType().getYear();
                }
            }
        }
        return 0;
    }

    public Double meanGPA() {
        double sum = 0;
        for(Education education : resume.educations){
            sum += education.getGrade();
        }
        return sum / resume.educations.size();
    }
    public int getExperienceYears() {
        int experienceYears = 0;
        for(Experience i : getResume().experiences) {
            if(i.getEnd_date_dateType() != null) {
                experienceYears += i.getEnd_date_dateType().getYear() - i.getStart_date_dateType().getYear();
                if(i.getEnd_date_dateType().getMonth() > i.getStart_date_dateType().getMonth()) {
                    experienceYears += 1;
                }
            }
        }
        return experienceYears;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consumer)) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(resume, consumer.resume);
    }

    public int hashCode() {
        return Objects.hash(resume);
    }

    public String toString() {
        return "Resume : " + resume + "\n\n";
    }
}

package com.example.alex.studentsrecycler.models;

/**
 * Created by Alex on 04.11.2016.
 */

public class Person {
    String name;
    String google;
    String git;
    public Person(String name, String google, String git) {
        this.name = name;
        this.google = google;
        this.git = git;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}

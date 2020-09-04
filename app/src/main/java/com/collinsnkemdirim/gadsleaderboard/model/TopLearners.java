package com.collinsnkemdirim.gadsleaderboard.model;

public class TopLearners {

    private String name;
    private int hours;
    private String country;

    public TopLearners(String name, int hours, String country) {
        this.name = name;
        this.hours = hours;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

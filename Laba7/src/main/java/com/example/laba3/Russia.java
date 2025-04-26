package com.example.laba3;

public class Russia implements Country {
    private long population;
    private String capital;
    private String governmentForm;

    public Russia() {
    }

    @Override
    public long getPopulation() {
        return population;
    }

    @Override
    public String getCapital() {
        return capital;
    }

    @Override
    public String getName() {
        return "Russia";
    }

    @Override
    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }
}
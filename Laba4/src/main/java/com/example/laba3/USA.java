package com.example.laba3;

public class USA implements Country {
    private long population;
    private String capital;

    public USA() {

    }

    @Override
    public long getPopulation() {
        return population;
    }

    @Override
    public String getCapital() {
        return capital;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void initMethod() {
        System.out.println("USA initialized");
    }

    public void destroyMethod() {
        System.out.println("USA destroyed");
    }
}
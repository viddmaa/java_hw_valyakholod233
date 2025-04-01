package com.example.laba3;

public class Russia implements Country {
    private long population;
    private String capital;

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

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void initMethod() {
        System.out.println("Russia initialized");
    }

    public void destroyMethod() {
        System.out.println("Russia destroyed");
    }
}

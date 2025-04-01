package com.example.laba3;

public class China implements Country {
    private long population;
    private String capital;

    public China(long population, String capital) {
        this.population = population;
        this.capital = capital;
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
}
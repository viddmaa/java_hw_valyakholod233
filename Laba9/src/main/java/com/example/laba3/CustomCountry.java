package com.example.laba3;

public class CustomCountry implements Country {
    private String name;
    private long population;
    private String capital;
    private String governmentForm;

    public CustomCountry(String name, long population, String capital, String governmentForm) {
        this.name = name;
        this.population = population;
        this.capital = capital;
        this.governmentForm = governmentForm;
    }

    @Override
    public String getName() {
        return name;
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
    public String getGovernmentForm() {
        return governmentForm;
    }
}

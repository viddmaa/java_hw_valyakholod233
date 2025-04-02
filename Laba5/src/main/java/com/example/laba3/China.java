package com.example.laba3;

import org.springframework.stereotype.Component;

@Component
public class China implements Country {
    private long population;
    private String capital;

    public China() {
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

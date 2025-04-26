package com.example.laba3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class CountryShow {
    private Country country;

    public CountryShow(Country country) {
        this.country = country;
    }

    @PostConstruct
    public void onInit() {
        System.out.println("CountryShow for " + country.getClass().getSimpleName() + " initialized");
    }

    public void show() {
        System.out.println();
        System.out.println("Страна: " + country.getClass().getSimpleName());
        System.out.println("Население: " + country.getPopulation() + " people");
        System.out.println("Столица: " + country.getCapital());
        System.out.println();
    }

    @PreDestroy
    public void onDestroy() {
        System.out.println("CountryShow for " + country.getClass().getSimpleName() + " destroyed");
    }
}
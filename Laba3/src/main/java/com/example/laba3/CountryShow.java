package com.example.laba3;

public class CountryShow {
    private Country country;

    public CountryShow(Country country) {
        this.country = country;
    }

    public void show() {
        System.out.println("Страна: " + country.getClass().getSimpleName());
        System.out.println("Население: " + country.getPopulation() + " people");
        System.out.println("Столица: " + country.getCapital());
    }
}

package com.example.laba3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Laba3Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CountryShow chinaShow = context.getBean("chinaShow", CountryShow.class);
        chinaShow.show();

        CountryShow russiaShow = context.getBean("russiaShow", CountryShow.class);
        russiaShow.show();

        CountryShow usaShow = context.getBean("usaShow", CountryShow.class);
        usaShow.show();

        context.close();
    }
}

    @Configuration
    @ComponentScan("com.example.laba3")
    class AppConfig {
    @Bean
    public Country china() {
        China china = new China();
        china.setPopulation(1409670000);
        china.setCapital("Pekin");
        return china;
    }

    @Bean
    public Country russia() {
        Russia russia = new Russia();
        russia.setPopulation(146199928);
        russia.setCapital("Moscow");
        return russia;
    }

    @Bean
    public Country usa() {
        USA usa = new USA();
        usa.setPopulation(341814420);
        usa.setCapital("Washington");
        return usa;
    }

    @Bean(name = "chinaShow")
    public CountryShow chinaShow(@Qualifier("china") Country country) {
        return new CountryShow(country);
    }

    @Bean(name = "russiaShow")
    public CountryShow russiaShow(@Qualifier("russia") Country country) {
        return new CountryShow(country);
    }

    @Bean(name = "usaShow")
    public CountryShow usaShow(@Qualifier("usa") Country country) {
        return new CountryShow(country);
    }
}
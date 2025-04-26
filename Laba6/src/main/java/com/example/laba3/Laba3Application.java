package com.example.laba3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@SpringBootApplication
public class Laba3Application {
    public static void main(String[] args) {
        SpringApplication.run(Laba3Application.class, args);
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
    @Scope("prototype")
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
}

@Controller
@RequestMapping("/countries")
class CountryController {
    private final List<Country> countries;

    @Autowired
    public CountryController(@Qualifier("china") Country china,
                             @Qualifier("russia") Country russia,
                             @Qualifier("usa") Country usa) {
        this.countries = List.of(china, russia, usa);
    }

    @GetMapping
        public String showCountries(Model model) {
            model.addAttribute("countries", countries);
            return "countries";
    }
}
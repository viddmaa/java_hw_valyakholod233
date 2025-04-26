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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        china.setCapital("Peking");
        china.setGovernmentForm("Socialist Republic");
        return china;
    }

    @Bean
    @Scope("prototype")
    public Country russia() {
        Russia russia = new Russia();
        russia.setPopulation(146199928);
        russia.setCapital("Moscow");
        russia.setGovernmentForm("Federal Republic");
        return russia;
    }

    @Bean
    public Country usa() {
        USA usa = new USA();
        usa.setPopulation(341814420);
        usa.setCapital("Washington");
        usa.setGovernmentForm("Federal Republic");
        return usa;
    }
}

@Controller
@RequestMapping("/countries")
class CountryController {
    private final Country[] countries;

    @Autowired
    public CountryController(@Qualifier("china") Country china,
                             @Qualifier("russia") Country russia,
                             @Qualifier("usa") Country usa) {
        this.countries = new Country[]{china, russia, usa};
    }

    @GetMapping
        public String showCountries(Model model) {
            model.addAttribute("countries", countries);
            return "countries";
    }

    @GetMapping("/{name}")
    public String showCountry(@PathVariable String name, Model model) {
        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(name)) {
                model.addAttribute("country", country);
                return "country";
            }
        }
        return "country-not-found";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam int a,
                            @RequestParam int b,
                            @RequestParam String op,
                            Model model) {
        int result = 0;
        String error = null;

        switch (op) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> {
                if (b != 0) {
                    result = a / b;
                } else {
                    error = "Ошибка: Деление на ноль";
                }
            }
            default -> {
                if (error == null)
                    error = "Ошибка: Недопустимая операция";
            }
        }

        if (error != null) {
            model.addAttribute("error", error);
        } else {
            model.addAttribute("result", result);
        }

        return "calculator";
    }
}
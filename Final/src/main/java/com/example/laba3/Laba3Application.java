package com.example.laba3;

import org.springframework.validation.BindingResult;
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
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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
    private final List<Country> countries = new ArrayList<>();

    @Autowired
    public CountryController(@Qualifier("china") Country china,
                             @Qualifier("russia") Country russia,
                             @Qualifier("usa") Country usa) {
        countries.add(china);
        countries.add(russia);
        countries.add(usa);
    }

    @GetMapping
    public String showCountries(Model model) {
        model.addAttribute("countries", countries);
        model.addAttribute("countryForm", new CountryForm());
        return "countries";
    }

    @PostMapping("/add")
    public String addCountry(@ModelAttribute CountryForm countryForm, BindingResult result, Model model) {
        if (countryForm.getPopulation() <= 0) {
            model.addAttribute("error", "Население должно быть положительным числом!");
            model.addAttribute("countries", countries);
            return "countries";
        }
        CustomCountry newCountry = new CustomCountry(
                countryForm.getName(),
                countryForm.getPopulation(),
                countryForm.getCapital(),
                countryForm.getGovernmentForm()
        );
        countries.add(newCountry);
        return "redirect:/countries";
    }

    @PostMapping("/delete")
    public String deleteCountryFromForm(@RequestParam String name) {
        Iterator<Country> iterator = countries.iterator();
        while (iterator.hasNext()) {
            Country country = iterator.next();
            if (country.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/countries";
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

    @PatchMapping("/{name}")
    @ResponseBody
    public String updateCountry(@PathVariable String name, @RequestBody CountryForm updatedForm) {
        for (Country country : countries) {
            if (country instanceof CustomCountry && country.getName().equalsIgnoreCase(name)) {
                if (updatedForm.getPopulation() > 0) {
                    ((CustomCountry) country).setPopulation(updatedForm.getPopulation());
                }
                ((CustomCountry) country).setCapital(updatedForm.getCapital());
                ((CustomCountry) country).setGovernmentForm(updatedForm.getGovernmentForm());
                return "Updated successfully.";
            }
        }
        return "Country not found.";
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public String deleteCountry(@PathVariable String name) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getName().equalsIgnoreCase(name)) {
                countries.remove(i);
                return "Deleted successfully.";
            }
        }
        return "Country not found.";
    }

    @GetMapping("/filter")
    public String filterByPopulation(@RequestParam long minPopulation, Model model) {
        List<Country> filtered = new ArrayList<>();
        for (Country country : countries) {
            if (country.getPopulation() > minPopulation) {
                filtered.add(country);
            }
        }
        model.addAttribute("countries", filtered);
        model.addAttribute("countryForm", new CountryForm());
        return "countries";
    }
}
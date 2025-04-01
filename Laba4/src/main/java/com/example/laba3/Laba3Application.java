package com.example.laba3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Laba3Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CountryShow chinaShow = context.getBean("chinaShow", CountryShow.class);
        chinaShow.show();

        CountryShow russiaShow = context.getBean("russiaShow", CountryShow.class);
        russiaShow.show();

        CountryShow usaShow = context.getBean("usaShow", CountryShow.class);
        usaShow.show();

        context.close();
    }
}
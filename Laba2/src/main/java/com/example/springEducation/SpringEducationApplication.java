package com.example.springEducation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringEducationApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml"
		);
		Person test = context.getBean("person", Person.class);

		System.out.println(test.toString());

		context.close();

		//SpringApplication.run(SpringEducationApplication.class, args);
	}

}

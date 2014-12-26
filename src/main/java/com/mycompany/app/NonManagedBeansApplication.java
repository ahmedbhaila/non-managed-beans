package com.mycompany.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/*
 * @author: Ahmed Bhaila
 * 
 * A simple domain object representing a Person. The bean will not be managed by Spring
 * 
 * Possible examples of beans outside of Spring
 * 1. JPA 
 * 2. Another container or process creating a new instance of this class
 */

@Configurable				// this special annotation lets spring know that this class will access spring bean(s)
class Person {
	protected String firstName;
	protected String lastName;
	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Autowired
	protected PersonFormatter personFormatter;			// Spring managed bean

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String formatName() {
		// look ma, using a spring managed bean
		return personFormatter.format(firstName, lastName);
	}
	
}

/*
 * A spring managed utility class that formats a person's name
 */
class PersonFormatter {
	public String format(String firstName, String lastName) {
		return lastName + ", " + firstName;
	}
}


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableLoadTimeWeaving(aspectjWeaving=AspectJWeaving.ENABLED)
@EnableSpringConfigured
public class NonManagedBeansApplication {

    public static void main(String[] args) {
    	SpringApplication.run(NonManagedBeansApplication.class, args);
    	
    	// creating a Person object outside of a spring container
    	Person person = new Person("Homer", "Simpson");
    	
    	// person accessing a spring managed bean to format name
    	System.out.println("Formatted name is " + person.formatName());
    }
    
    @Bean
    public PersonFormatter personFormatter() {
    	return new PersonFormatter();				// I am a spring managed bean
    }
}

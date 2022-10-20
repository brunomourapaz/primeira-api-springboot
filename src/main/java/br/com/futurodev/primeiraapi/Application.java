package br.com.futurodev.primeiraapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

         TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // É a linha principal que roda o projeto Java Spring Boot
        SpringApplication.run(Application.class, args);
    }
}

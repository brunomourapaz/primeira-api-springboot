package br.com.futurodev.primeiraapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.TimeZone;

/**
 *
 * Spring Boot application starter class
 */
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // remover a propriedade exclude para funcionar token
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

         TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // É a linha principal que roda o projeto Java Spring Boot
        SpringApplication.run(Application.class, args);

        System.out.println(new BCryptPasswordEncoder().encode("203040"));
    }
}

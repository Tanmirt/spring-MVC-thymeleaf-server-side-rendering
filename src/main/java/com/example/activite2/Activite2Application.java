package com.example.activite2;

import com.example.activite2.Entity.Patient;
import com.example.activite2.repository.PatientRepo;
import com.example.activite2.sec.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.lang.Math;
import java.util.Random;

@SpringBootApplication
public class Activite2Application implements CommandLineRunner {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private SecurityService securityService;

    public static void main(String[] args) {

        SpringApplication.run(Activite2Application.class, args);
 
    }

    @Override
    public void run(String... args) throws Exception {
        
        Random ran = new Random();
        int x;
        for (int i = 0; i<100; i++) {
            x = ran.nextInt(90) + 5;
            patientRepo.save(
                    new Patient(null, "sami_" + (i + 1), new Date(),
                            Math.random() > 0.5 ? true : false, x, Math.random() > 0.5 ? "HOMME" : "FEMME"));
        }

        securityService.saveNewUser("user1", "gnaou", "gnaou");
        securityService.saveNewUser("sami", "gnaou", "gnaou");


        securityService.saveNewRole("USER", "utilisateur simple");
        securityService.saveNewRole("ADMIN", "Administrateur de l'application");


        securityService.addRoleToUser("sami", "ADMIN");
        securityService.addRoleToUser("sami", "USER");
        securityService.addRoleToUser("user1", "USER");

        securityService.saveNewUser("admin", "gnaou", "gnaou");

        securityService.addRoleToUser("admin", "ADMIN");
        securityService.addRoleToUser("admin", "USER");


        


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}

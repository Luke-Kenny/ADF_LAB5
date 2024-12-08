package com.example.adf_lab5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Author: Luke Kenny
// StudentID: R00212866

@SpringBootApplication
public class AdfLab5Application {

    public static void main(String[] args) {
        SpringApplication.run(AdfLab5Application.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("password");
    }

}

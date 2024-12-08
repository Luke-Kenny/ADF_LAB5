package com.example.adf_lab5.dto;

import jakarta.validation.constraints.NotBlank;

// Make sure this file is named ChangePetNameDTO.java
public class ChangePetNameDTO {

    @NotBlank(message = "New name cannot be blank")
    private String newName;

    // Getters and Setters (or use Lombok if preferred)
    public String getNewName() {
        return newName;
    }
}

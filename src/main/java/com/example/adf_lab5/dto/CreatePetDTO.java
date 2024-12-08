package com.example.adf_lab5.dto;

import jakarta.validation.constraints.*;

public record CreatePetDTO(
        @NotBlank String name,
        @NotBlank String animalType,
        @NotBlank String breed,
        @Min(0) int age
) {}

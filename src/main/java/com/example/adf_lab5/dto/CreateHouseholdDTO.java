package com.example.adf_lab5.dto;

import jakarta.validation.constraints.*;

public record CreateHouseholdDTO(
        @NotBlank String eircode,
        @Min(1) @Max(20) int numberOfOccupants,
        @Min(1) @Max(20) int maxNumberOfOccupants,
        boolean ownerOccupied
) {}

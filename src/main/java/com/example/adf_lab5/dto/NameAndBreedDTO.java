package com.example.adf_lab5.dto;

public class NameAndBreedDTO {
    private final String name;
    private final String animalType;
    private final String breed;

    public NameAndBreedDTO(String name, String animalType, String breed) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getBreed() {
        return breed;
    }
}

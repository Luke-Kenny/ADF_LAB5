package com.example.adf_lab5.service;

import com.example.adf_lab5.dto.CreatePetDTO;
import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.dto.PetStatisticsDTO;
import com.example.adf_lab5.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    Pet createPet(CreatePetDTO petDTO);
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet updatePet(Long id, Pet petDetails);
    void deletePetById(Long id);
    List<Pet> findPetsByBreed(String breed);
    List<Pet> findPetsByAnimalType(String animalType);

    Pet changePetName(Long id, String newName);

    // DTOs
    List<NameAndBreedDTO> getNameAndBreedOnly();
    PetStatisticsDTO getPetStatistics();
}

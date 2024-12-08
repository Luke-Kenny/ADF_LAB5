package com.example.adf_lab5.service.impl;

import com.example.adf_lab5.dto.CreatePetDTO;
import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.dto.PetStatisticsDTO;
import com.example.adf_lab5.entity.Pet;
import com.example.adf_lab5.repository.PetRepository;
import com.example.adf_lab5.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(CreatePetDTO petDTO) {
        if (petDTO == null) {
            throw new IllegalArgumentException("Pet data cannot be null");
        }

        Pet pet = new Pet();
        pet.setName(petDTO.name());
        pet.setAnimalType(petDTO.animalType());
        pet.setBreed(petDTO.breed());
        pet.setAge(petDTO.age());
        return petRepository.save(pet);
    }

    @Override
    public Pet changePetName(Long id, String newName) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        pet.setName(newName); // Only update the name
        return petRepository.save(pet); // Save the updated entity
    }


    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = getPetById(id);
        pet.setName(petDetails.getName());
        pet.setAnimalType(petDetails.getAnimalType());
        pet.setBreed(petDetails.getBreed());
        pet.setAge(petDetails.getAge());
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }


    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    @Override
    public List<NameAndBreedDTO> getNameAndBreedOnly() {
        return petRepository.findNameAndBreedOnly();
    }

    @Override
    public PetStatisticsDTO getPetStatistics() {
        double averageAge = petRepository.findAverageAge();
        int oldestAge = petRepository.findOldestAge();
        long totalCount = petRepository.findTotalCount();

        return new PetStatisticsDTO(averageAge, oldestAge, totalCount);
    }
}

package com.example.adf_lab5.integration;

import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.dto.PetStatisticsDTO;
import com.example.adf_lab5.entity.Pet;
import com.example.adf_lab5.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PetIntegrationTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    void testCreateAndRetrievePet() {
        Pet pet = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        petRepository.save(pet);

        Pet foundPet = petRepository.findById(pet.getId()).orElse(null);
        assertNotNull(foundPet);
        assertEquals("Buddy", foundPet.getName());
        assertEquals("Dog", foundPet.getAnimalType());
    }

    @Test
    void testFindPetsByAnimalType() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Charlie", "Dog", "Beagle", 4, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        List<Pet> dogs = petRepository.findByAnimalType("Dog");
        assertNotNull(dogs);
        assertEquals(2, dogs.size());
    }

    @Test
    void testFindNameAndBreedOnly() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Mittens", "Cat", "Siamese", 2, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        List<NameAndBreedDTO> results = petRepository.findNameAndBreedOnly();
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    void testGetPetStatistics() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Charlie", "Dog", "Beagle", 4, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        PetStatisticsDTO statistics = new PetStatisticsDTO(
                petRepository.findAverageAge(),
                petRepository.findOldestAge(),
                petRepository.findTotalCount()
        );

        assertNotNull(statistics);
        assertEquals(3.5, statistics.getAverageAge());
        assertEquals(4, statistics.getOldestAge());
        assertEquals(2, statistics.getTotalCount());
    }
}

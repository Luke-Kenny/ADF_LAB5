package com.example.adf_lab5.repository;

import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    void cleanDatabase() {
        petRepository.deleteAll(); // Clear all records from the database before each test
    }

    @Test
    void testFindByAnimalType() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Charlie", "Dog", "Beagle", 2, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        List<Pet> result = petRepository.findByAnimalType("Dog");
        assertEquals(2, result.size());
    }

    @Test
    void testFindNameAndBreedOnly() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Mittens", "Cat", "Siamese", 2, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        List<NameAndBreedDTO> result = petRepository.findNameAndBreedOnly();
        assertEquals(2, result.size());
        assertEquals("Buddy", result.get(0).getName());
        assertEquals("Golden Retriever", result.get(0).getBreed());
    }

    @Test
    void testFindAverageAge() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Charlie", "Dog", "Beagle", 5, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        double averageAge = petRepository.findAverageAge();
        assertEquals(4.0, averageAge, 0.01);
    }

    @Test
    void testFindOldestAge() {
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, null);
        Pet pet2 = new Pet(null, "Charlie", "Dog", "Beagle", 5, null);
        petRepository.save(pet1);
        petRepository.save(pet2);

        int oldestAge = petRepository.findOldestAge();
        assertEquals(5, oldestAge);
    }


}

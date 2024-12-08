package com.example.adf_lab5.repository;

import com.example.adf_lab5.entity.Household;
import com.example.adf_lab5.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @BeforeEach
    void setUp() {
        householdRepository.deleteAll(); // Ensure database starts clean
    }

    @Test
    void testFindByEircodeWithPets() {
        // Set up household with pets
        Household household = new Household("E12345", 3, 5, true);
        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, household);
        Pet pet2 = new Pet(null, "Mittens", "Cat", "Siamese", 2, household);
        household.setPets(List.of(pet1, pet2));

        householdRepository.save(household);

        // Test the method
        Optional<Household> result = householdRepository.findByEircodeWithPets("E12345");

        assertTrue(result.isPresent());
        Household foundHousehold = result.get();
        assertEquals("E12345", foundHousehold.getEircode());
        assertEquals(2, foundHousehold.getPets().size());
    }

    @Test
    void testFindHouseholdsWithNoPets() {
        // Set up households
        Household householdWithPets = new Household("E12345", 3, 5, true);
        Household householdNoPets = new Household("E54321", 2, 4, false);

        Pet pet1 = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, householdWithPets);
        householdWithPets.setPets(new ArrayList<>(List.of(pet1)));
        householdRepository.save(householdWithPets);
        householdRepository.save(householdNoPets);

        // Test the method
        List<Household> result = householdRepository.findHouseholdsWithNoPets();

        assertEquals(1, result.size());
        assertEquals("E54321", result.get(0).getEircode());
    }
}

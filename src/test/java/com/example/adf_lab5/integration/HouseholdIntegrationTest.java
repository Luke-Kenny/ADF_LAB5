package com.example.adf_lab5.integration;

import com.example.adf_lab5.entity.Household;
import com.example.adf_lab5.repository.HouseholdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HouseholdIntegrationTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    void testCreateAndRetrieveHousehold() {
        Household household = new Household("D02XY45", 3, 5, true);
        householdRepository.save(household);

        Household foundHousehold = householdRepository.findById("D02XY45").orElse(null);
        assertNotNull(foundHousehold);
        assertEquals("D02XY45", foundHousehold.getEircode());
        assertEquals(3, foundHousehold.getNumberOfOccupants());
    }

    @Test
    void testFindHouseholdsWithNoPets() {
        Household household1 = new Household("T12AB34", 2, 4, true);
        Household household2 = new Household("A94B6F3", 4, 6, false);
        householdRepository.save(household1);
        householdRepository.save(household2);

        List<Household> households = householdRepository.findHouseholdsWithNoPets();
        assertNotNull(households);
        assertEquals(2, households.size());
    }
}

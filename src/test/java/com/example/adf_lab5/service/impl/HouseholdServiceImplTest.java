package com.example.adf_lab5.service.impl;

import com.example.adf_lab5.dto.CreateHouseholdDTO;
import com.example.adf_lab5.entity.Household;
import com.example.adf_lab5.repository.HouseholdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseholdServiceImplTest {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    private CreateHouseholdDTO household;

    @Test
    void testFindHouseholdByEircodeNotFound() {
        when(householdRepository.findById("INVALID")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            householdService.findHouseholdByEircode("INVALID");
        });

        assertEquals("Household not found", exception.getMessage());
        verify(householdRepository, times(1)).findById("INVALID");
    }

    @Test
    void testFindHouseholdsWithNoPets() {
        List<Household> households = Arrays.asList(
                new Household("T12AB34", 2, 4, true),
                new Household("F12GH89", 1, 2, false)
        );

        when(householdRepository.findHouseholdsWithNoPets()).thenReturn(households);

        List<Household> result = householdService.findHouseholdsWithNoPets();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(householdRepository, times(1)).findHouseholdsWithNoPets();
    }

    @Test
    void testGetAllHouseholds() {
        List<Household> households = Arrays.asList(
                new Household("D02XY45", 3, 5, true),
                new Household("A94B6F3", 4, 6, false)
        );

        when(householdRepository.findAll()).thenReturn(households);

        List<Household> result = householdService.getAllHouseholds();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(householdRepository, times(1)).findAll();
    }



    @Test
    void testDeleteHouseholdByEircode() {
        doNothing().when(householdRepository).deleteById("D02XY45");

        householdService.deleteHouseholdByEircode("D02XY45");

        verify(householdRepository, times(1)).deleteById("D02XY45");
    }

    @Test
    void testSaveNullHousehold() {
        assertThrows(IllegalArgumentException.class, () -> {
            householdService.saveHousehold(null);
        });
        verify(householdRepository, times(0)).save(any(Household.class));
    }

    @Test
    void testExceptionInMethod() {
        when(householdRepository.findById("INVALID")).thenThrow(new RuntimeException("Simulated exception"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            householdService.findHouseholdByEircode("INVALID");
        });

        assertEquals("Simulated exception", exception.getMessage());
        verify(householdRepository, times(1)).findById("INVALID");
    }


}



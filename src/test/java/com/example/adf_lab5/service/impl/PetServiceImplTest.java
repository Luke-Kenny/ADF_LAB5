package com.example.adf_lab5.service.impl;

import com.example.adf_lab5.dto.CreatePetDTO;
import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.dto.PetStatisticsDTO;
import com.example.adf_lab5.entity.Pet;
import com.example.adf_lab5.repository.PetRepository;
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

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private CreatePetDTO createPetDTO;
    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize CreatePetDTO
        createPetDTO = new CreatePetDTO(
                "Buddy",
                "Dog",
                "Golden Retriever",
                3
        );

        // Initialize Pet Entity
        pet = new Pet(
                1L,
                "Buddy",
                "Dog",
                "Golden Retriever",
                3,
                null
        );
    }

    @Test
    void testCreatePet() {
        when(petRepository.save(any(Pet.class))).thenAnswer(invocation -> {
            Pet savedPet = invocation.getArgument(0);
            savedPet.setId(1L);
            return savedPet;
        });

        Pet createdPet = petService.createPet(createPetDTO);

        assertNotNull(createdPet);
        assertEquals("Buddy", createdPet.getName());
        assertEquals("Dog", createdPet.getAnimalType());
        assertEquals("Golden Retriever", createdPet.getBreed());
        assertEquals(3, createdPet.getAge());

        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    void testGetAllPets() {
        List<Pet> pets = Arrays.asList(
                pet,
                new Pet(2L, "Mittens", "Cat", "Siamese", 2, null)
        );

        when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.getAllPets();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void testGetPetById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Pet result = petService.getPetById(1L);

        assertNotNull(result);
        assertEquals("Buddy", result.getName());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPetByIdNotFound() {
        when(petRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> petService.getPetById(99L));

        assertEquals("Pet not found", exception.getMessage());
        verify(petRepository, times(1)).findById(99L);
    }

    @Test
    void testUpdatePet() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        pet.setAge(4);
        Pet updatedPet = petService.updatePet(1L, pet);

        assertEquals(4, updatedPet.getAge());
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void testDeletePetById() {
        doNothing().when(petRepository).deleteById(1L);

        petService.deletePetById(1L);

        verify(petRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindPetsByAnimalType() {
        List<Pet> pets = Arrays.asList(
                pet,
                new Pet(3L, "Charlie", "Dog", "Beagle", 2, null)
        );

        when(petRepository.findByAnimalType("Dog")).thenReturn(pets);

        List<Pet> result = petService.findPetsByAnimalType("Dog");

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(petRepository, times(1)).findByAnimalType("Dog");
    }

    @Test
    void testGetNameAndBreedOnly() {
        List<NameAndBreedDTO> dtos = Arrays.asList(
                new NameAndBreedDTO("Buddy", "Dog", "Golden Retriever"),
                new NameAndBreedDTO("Mittens", "Cat", "Siamese")
        );

        when(petRepository.findNameAndBreedOnly()).thenReturn(dtos);

        List<NameAndBreedDTO> result = petService.getNameAndBreedOnly();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(petRepository, times(1)).findNameAndBreedOnly();
    }

    @Test
    void testGetPetStatistics() {
        when(petRepository.findAverageAge()).thenReturn(3.5);
        when(petRepository.findOldestAge()).thenReturn(5);
        when(petRepository.findTotalCount()).thenReturn(2L);

        PetStatisticsDTO stats = petService.getPetStatistics();

        assertNotNull(stats);
        assertEquals(3.5, stats.getAverageAge());
        assertEquals(5, stats.getOldestAge());
        assertEquals(2L, stats.getTotalCount());

        verify(petRepository, times(1)).findAverageAge();
        verify(petRepository, times(1)).findOldestAge();
        verify(petRepository, times(1)).findTotalCount();
    }

    @Test
    void testChangePetName() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet updatedPet = petService.changePetName(1L, "Max");

        assertEquals("Max", updatedPet.getName());
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(1)).save(pet);
    }
}

package com.example.adf_lab5.controller;

import com.example.adf_lab5.dto.ChangePetNameDTO;
import com.example.adf_lab5.dto.CreatePetDTO;
import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.dto.PetStatisticsDTO;
import com.example.adf_lab5.entity.Pet;
import com.example.adf_lab5.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody CreatePetDTO petDTO) {
        return ResponseEntity.ok(petService.createPet(petDTO));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Pet> changePetName(
            @PathVariable Long id,
            @Valid @RequestBody ChangePetNameDTO changePetNameDTO) {
        Pet updatedPet = petService.changePetName(id, changePetNameDTO.getNewName());
        return ResponseEntity.ok(updatedPet);
    }


    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        return ResponseEntity.ok(petService.updatePet(id, petDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-animal-type")
    public ResponseEntity<List<Pet>> getPetsByAnimalType(@RequestParam String animalType) {
        return ResponseEntity.ok(petService.findPetsByAnimalType(animalType));
    }


    @GetMapping("/by-breed")
    public ResponseEntity<List<Pet>> getPetsByBreed(@RequestParam String breed) {
        return ResponseEntity.ok(petService.findPetsByBreed(breed));
    }


    @GetMapping("/names-and-breeds")
    public ResponseEntity<List<NameAndBreedDTO>> getNameAndBreedOnly() {
        return ResponseEntity.ok(petService.getNameAndBreedOnly());
    }

    @GetMapping("/statistics")
    public ResponseEntity<PetStatisticsDTO> getPetStatistics() {
        return ResponseEntity.ok(petService.getPetStatistics());
    }

}

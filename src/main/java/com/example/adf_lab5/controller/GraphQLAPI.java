package com.example.adf_lab5.controller;

import com.example.adf_lab5.dto.CreateHouseholdDTO;
import com.example.adf_lab5.dto.CreatePetDTO;
import com.example.adf_lab5.entity.Household;
import com.example.adf_lab5.entity.Pet;
import com.example.adf_lab5.service.HouseholdService;
import com.example.adf_lab5.service.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLAPI {

    private final HouseholdService householdService;
    private final PetService petService;


    @QueryMapping
    @Secured("ROLE_USER") // Accessible to USER and ADMIN roles
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    @Secured("ROLE_USER") // Accessible to USER and ADMIN roles
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @MutationMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Household createHousehold(@Valid @Argument("household") CreateHouseholdDTO createHouseholdDTO) {
        return householdService.saveHousehold(createHouseholdDTO);
    }

    @MutationMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Pet createPet(@Valid @Argument("pet") CreatePetDTO createPetDTO) {
        return petService.createPet(createPetDTO);
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public boolean deletePet(@Argument Long id) {
        petService.deletePetById(id);
        return true;
    }
}

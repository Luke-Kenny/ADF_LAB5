package com.example.adf_lab5.controller;

import com.example.adf_lab5.dto.CreateHouseholdDTO;
import com.example.adf_lab5.entity.Household;
import com.example.adf_lab5.service.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHouseholdByEircode(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.findHouseholdByEircode(eircode));
    }

    @GetMapping("/{eircode}/with-pets")
    public ResponseEntity<Household> getHouseholdWithPets(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.findHouseholdByEircode(eircode));
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody CreateHouseholdDTO householdDTO) {
        return ResponseEntity.ok(householdService.saveHousehold(householdDTO));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
        return ResponseEntity.noContent().build();
    }
}

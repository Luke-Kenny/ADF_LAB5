package com.example.adf_lab5.service.impl;

import com.example.adf_lab5.dto.CreateHouseholdDTO;
import com.example.adf_lab5.entity.Household;
import com.example.adf_lab5.repository.HouseholdRepository;
import com.example.adf_lab5.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household findHouseholdByEircode(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode)
                .orElseThrow(() -> new RuntimeException("Household not found"));
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household saveHousehold(CreateHouseholdDTO householdDTO) {
        Household household = new Household(
                householdDTO.eircode(),
                householdDTO.numberOfOccupants(),
                householdDTO.maxNumberOfOccupants(),
                householdDTO.ownerOccupied()
        );

        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) {
        householdRepository.deleteById(eircode);
    }
}

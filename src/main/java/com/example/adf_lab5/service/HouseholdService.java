package com.example.adf_lab5.service;

import com.example.adf_lab5.dto.CreateHouseholdDTO;
import com.example.adf_lab5.entity.Household;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseholdService {
    Household findHouseholdByEircode(String eircode);
    List<Household> findHouseholdsWithNoPets();
    List<Household> getAllHouseholds();
    Household saveHousehold(CreateHouseholdDTO householdDTO);
    void deleteHouseholdByEircode(String eircode);
}

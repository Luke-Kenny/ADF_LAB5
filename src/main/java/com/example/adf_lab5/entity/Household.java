package com.example.adf_lab5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Household {
    @Id
    private String eircode;

    @Column(nullable = false, name = "number_of_occupants")
    private int numberOfOccupants;

    @Column(nullable = false, name = "max_number_of_occupants")
    private int maxNumberOfOccupants;

    @Column(nullable = false, name = "owner_occupied")
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Pet> pets;

    // Constructor without pets for creating new households
    public Household(String eircode, int numberOfOccupants, int maxNumberOfOccupants, boolean ownerOccupied) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.ownerOccupied = ownerOccupied;
        this.pets = new ArrayList<>();
    }
}

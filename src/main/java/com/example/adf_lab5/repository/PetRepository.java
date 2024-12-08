package com.example.adf_lab5.repository;

import com.example.adf_lab5.dto.NameAndBreedDTO;
import com.example.adf_lab5.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAnimalType(String animalType);
    List<Pet> findByBreedOrderByAge(String breed);

    @Query("SELECT new com.example.adf_lab5.dto.NameAndBreedDTO(p.name, p.animalType, p.breed) FROM Pet p")
    List<NameAndBreedDTO> findNameAndBreedOnly();

    @Query("SELECT AVG(p.age) FROM Pet p")
    double findAverageAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    int findOldestAge();

    @Query("SELECT COUNT(p) FROM Pet p")
    long findTotalCount();
}

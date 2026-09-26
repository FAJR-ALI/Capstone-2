package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    List<Donor> findByCity(String city);
    List<Donor> findByBloodType(String bloodType);
}

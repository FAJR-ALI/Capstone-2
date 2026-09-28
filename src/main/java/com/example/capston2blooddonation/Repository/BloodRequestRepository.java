package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodRequestRepository extends JpaRepository <BloodRequest, Integer> {

    List<BloodRequest> findByCity(String city);
    List<BloodRequest> findByBloodType(String bloodType);
    List<BloodRequest> findByQuantityGreaterThanEqual(Integer quantity);
}

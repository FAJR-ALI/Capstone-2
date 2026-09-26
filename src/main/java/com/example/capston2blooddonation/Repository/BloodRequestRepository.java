package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRequestRepository extends JpaRepository <BloodRequest, Integer> {

}

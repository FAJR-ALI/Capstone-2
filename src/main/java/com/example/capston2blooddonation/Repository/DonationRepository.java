package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    Long countByDonorId(Integer donorId);

}

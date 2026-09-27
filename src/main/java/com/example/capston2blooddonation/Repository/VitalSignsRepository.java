package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.Donor;
import com.example.capston2blooddonation.Model.VitalSigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Integer> {

    List<VitalSigns> findByDonorId(Integer donorId);
    VitalSigns findTopByDonorIdOrderByIdDesc(Integer dinorId);

    @Query("""
    SELECT v.donor
    FROM VitalSigns v
    WHERE v.id IN (
        SELECT MAX(v2.id)
        FROM VitalSigns v2
        GROUP BY v2.donor.id
    )
    AND v.heartRate BETWEEN 60 AND 100
    AND v.temperature BETWEEN 36 AND 37.5
    AND v.hemoglobinLevel >= 12.5
""")
    List<Donor> findEligibleDonors();


}

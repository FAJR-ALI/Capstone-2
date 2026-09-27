package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {

    @Query("SELECT d FROM Donor d WHERE d.city =:city")
    List<Donor> findByCity(@Param("city") String city);

    List<Donor> findByBloodType(String bloodType);
}

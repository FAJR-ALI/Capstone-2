package com.example.capston2blooddonation.Repository;

import com.example.capston2blooddonation.Model.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface BloodInventoryRepository extends JpaRepository<BloodInventory, Integer> {

    List<BloodInventory> findByBloodType(String bloodType);

    List<BloodInventory> findByCity(String city);

    @Query("SELECT COALESCE(SUM(b.quantity), 0) FROM BloodInventory b WHERE b.bloodType = :bloodType")
    Integer getTotalQuantityByBloodType(@Param("bloodType") String bloodType);

    BloodInventory findFirstByBloodType(String bloodType);
}

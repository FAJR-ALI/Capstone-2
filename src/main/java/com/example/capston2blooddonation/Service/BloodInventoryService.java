package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.Model.BloodInventory;
import com.example.capston2blooddonation.Repository.BloodInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodInventoryService {
    private final BloodInventoryRepository bloodInventoryRepository;

    public List<BloodInventory> getAllBloodInventory() {
        return bloodInventoryRepository.findAll();
    }

    public BloodInventory getBloodInventoryById(Integer id) {
        return bloodInventoryRepository.findById(id).orElse(null);
    }

    public BloodInventory addBloodInventory(BloodInventory bloodInventory) {
        return bloodInventoryRepository.save(bloodInventory);
    }

    public BloodInventory updateBloodInventory(Integer id, BloodInventory newBloodInventory) {
        BloodInventory oldBloodInventory = bloodInventoryRepository.findById(id).orElse(null);

        if (oldBloodInventory == null) {
            return null;
        }
        oldBloodInventory.setBloodType(newBloodInventory.getBloodType());
        oldBloodInventory.setQuantity(newBloodInventory.getQuantity());
        oldBloodInventory.setCity(newBloodInventory.getCity());
        return bloodInventoryRepository.save(oldBloodInventory);
    }

    public boolean deleteBloodInventory(Integer id) {
        if (!bloodInventoryRepository.existsById(id)) {
            return false;
        }
        bloodInventoryRepository.deleteById(id);
        return true;
    }

    public List<BloodInventory> getInventoryByBloodType(String bloodType) {
        return bloodInventoryRepository.findByBloodType(bloodType);
    }

    public List<BloodInventory> getInventoryByCity(String city) {
        return bloodInventoryRepository.findByCity(city);
    }

    public Integer getTotalQuantityByBloodType(String bloodType) {
        return bloodInventoryRepository.getTotalQuantityByBloodType(bloodType);
    }

    public Integer getTotalQuantityByCity(String city) {
        return bloodInventoryRepository.getTotalQuantityByCity(city);
    }
}

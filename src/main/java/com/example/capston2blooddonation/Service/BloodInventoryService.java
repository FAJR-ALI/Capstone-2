package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.ApiResponse.ApiException;
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
        BloodInventory p = bloodInventoryRepository.findById(id).orElse(null);
        if (p ==null){
            throw new ApiException("no Blood inventory found for provided BloodType");
        }
        return p;
    }

    public BloodInventory addBloodInventory(BloodInventory bloodInventory) {
        return bloodInventoryRepository.save(bloodInventory);
    }

    public BloodInventory updateBloodInventory(Integer id, BloodInventory newBloodInventory) {
        BloodInventory oldBloodInventory = bloodInventoryRepository.findById(id).orElse(null);
        if (oldBloodInventory == null) {
            throw new ApiException("no Blood inventory found");
        }
        oldBloodInventory.setBloodType(newBloodInventory.getBloodType());
        oldBloodInventory.setQuantity(newBloodInventory.getQuantity());
        oldBloodInventory.setCity(newBloodInventory.getCity());
        return bloodInventoryRepository.save(oldBloodInventory);
    }

    public void deleteBloodInventory(Integer id) {
        if (!bloodInventoryRepository.existsById(id)) {
           throw new ApiException("No Inventory found to delete");
        }
        bloodInventoryRepository.deleteById(id);
    }

    public List<BloodInventory> getInventoryByBloodType(String bloodType) {
        List<BloodInventory> pp = bloodInventoryRepository.findByBloodType(bloodType);
        if(pp.isEmpty()){
            throw new ApiException("No Inventory found with provided BloodType and City");
        }
        return pp;
    }

    public List<BloodInventory> getInventoryByCity(String city) {
        List<BloodInventory> ll = bloodInventoryRepository.findByCity(city);
        if(ll.isEmpty()){
            throw new ApiException("No Inventory found with provided City ");
        }
        return ll;
    }

    public Integer getTotalQuantityByBloodType(String bloodType) {
        Integer n = bloodInventoryRepository.getTotalQuantityByBloodType(bloodType);
        if(n == 0){
            throw new ApiException("0 Quantity found for the provided blood type");
        }
        return n;
    }

    public Integer getTotalQuantityByCity(String city) {
        Integer m = bloodInventoryRepository.getTotalQuantityByCity(city);
        if(m == 0){
            throw new ApiException("No Quantity found with provided City");
        }
        return m;
    }
}

package com.example.capston2blooddonation.Controller;

import com.example.capston2blooddonation.ApiResponse.ApiResponse;
import com.example.capston2blooddonation.Model.BloodInventory;
import com.example.capston2blooddonation.Service.BloodInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllBloodInventory() {

        return ResponseEntity.status(200).body(bloodInventoryService.getAllBloodInventory());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBloodInventoryById(@PathVariable Integer id) {
        BloodInventory bloodInventory = bloodInventoryService.getBloodInventoryById(id);
        return ResponseEntity.status(200).body(bloodInventory);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBloodInventory(@Valid @RequestBody BloodInventory bloodInventory) {
        bloodInventoryService.addBloodInventory(bloodInventory);
        return ResponseEntity.status(200).body(new ApiResponse("Blood Inventory Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBloodInventory(@PathVariable Integer id, @Valid @RequestBody BloodInventory newBloodInventory) {
        BloodInventory updatedBloodInventory = bloodInventoryService.updateBloodInventory(id, newBloodInventory);
        return ResponseEntity.status(200).body(updatedBloodInventory);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBloodInventory(@PathVariable Integer id) {
        bloodInventoryService.deleteBloodInventory(id);
        return ResponseEntity.status(200).body(new ApiResponse("Blood Inventory Deleted Successfully"));
    }

    @GetMapping("/blood-type/{bloodType}")
    public ResponseEntity<?> getInventoryByBloodType(@PathVariable String bloodType) {
        return ResponseEntity.status(200).body(bloodInventoryService.getInventoryByBloodType(bloodType));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getInventoryByCity(@PathVariable String city) {
        return ResponseEntity.status(200).body(bloodInventoryService.getInventoryByCity(city));
    }

    @GetMapping("/quantity/{bloodType}")
    public ResponseEntity<?> getTotalQuantityByBloodType(@PathVariable String bloodType) {
        return ResponseEntity.status(200).body(bloodInventoryService.getTotalQuantityByBloodType(bloodType));
    }

    @GetMapping("/quantity/city/{city}")
    public ResponseEntity<?> getTotalQuantityByCity(@PathVariable String city) {
        return ResponseEntity.status(200).body(bloodInventoryService.getTotalQuantityByCity(city));
    }
}

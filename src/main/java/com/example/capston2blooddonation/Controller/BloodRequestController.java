package com.example.capston2blooddonation.Controller;

import com.example.capston2blooddonation.ApiResponse.ApiResponse;
import com.example.capston2blooddonation.Model.BloodRequest;
import com.example.capston2blooddonation.Service.BloodRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bloodrequest")
@RequiredArgsConstructor
public class BloodRequestController {

    private final BloodRequestService bloodRequestService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllBloodRequests() {
        return ResponseEntity.status(200).body(bloodRequestService.getAllBloodRequest());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBloodRequestById(@PathVariable Integer id) {
        BloodRequest bloodRequest = bloodRequestService.getBloodRequestById(id);
        return ResponseEntity.status(200).body(bloodRequest);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBloodRequest(@Valid @RequestBody BloodRequest newBloodRequest) {
        bloodRequestService.addBloodRequest(newBloodRequest);
        return ResponseEntity.status(200).body(new ApiResponse("BloodRequest Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBloodRequest(@PathVariable Integer id, @Valid @RequestBody BloodRequest newBloodRequest) {
        BloodRequest updatedBloodRequest = bloodRequestService.updateBloodRequest(id, newBloodRequest);

        return ResponseEntity.status(200).body(updatedBloodRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBloodRequest(@PathVariable Integer id){
       bloodRequestService.deleteBloodRequest(id);
        return ResponseEntity.status(200).body(new ApiResponse("BloodRequest Deleted Successfully "));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getBloodRequestsByCity(@PathVariable String city) {
        return ResponseEntity.status(200).body(bloodRequestService.getBloodRequestsByCity(city));
    }

    @GetMapping("/blood-type/{bloodType}")
    public ResponseEntity<?> getBloodRequestsByBloodType(@PathVariable String bloodType) {
        return ResponseEntity.status(200).body(bloodRequestService.getBloodRequestsByBloodType(bloodType));
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity<?> getBloodRequestsByQuantity(@PathVariable Integer quantity) {
        return ResponseEntity.status(200).body(bloodRequestService.getBloodRequestsByQuantity(quantity));
    }
}
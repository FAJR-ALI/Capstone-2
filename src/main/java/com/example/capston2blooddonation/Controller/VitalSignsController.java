package com.example.capston2blooddonation.Controller;

import com.example.capston2blooddonation.ApiResponse.ApiResponse;
import com.example.capston2blooddonation.Model.VitalSigns;
import com.example.capston2blooddonation.Service.VitalSignsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vitals")
@RequiredArgsConstructor
public class VitalSignsController {

    private final VitalSignsService vitalSignsService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllVitalSigns() {
        return ResponseEntity.status(200).body(vitalSignsService.getAllVitalSigns());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getVitalSignsById(@PathVariable Integer id) {
        VitalSigns vitalSigns =vitalSignsService.getVitalSignsById(id);

        return ResponseEntity.status(200).body(vitalSigns);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVitalSigns(@Valid @RequestBody VitalSigns vitalSigns) {
        vitalSignsService.addVitalSigns(vitalSigns);
        return ResponseEntity.status(200).body(new ApiResponse("Vital Signs Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVitalSigns(@PathVariable Integer id, @Valid @RequestBody VitalSigns newVitalSigns) {
        VitalSigns updatedVitalSigns = vitalSignsService.updateVitalSigns(id, newVitalSigns);
        return ResponseEntity.status(200).body(updatedVitalSigns);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVitalSigns(@PathVariable Integer id) {
        vitalSignsService.deleteVitalSigns(id);
        return ResponseEntity.status(200).body(new ApiResponse("Vital Signs Deleted Successfully"));
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<?> getVitalSignsByDonor(@PathVariable Integer donorId) {
        return ResponseEntity.status(200).body(vitalSignsService.getVitalSignsByDonor(donorId));
    }

    @GetMapping("/eligibility/{donorId}")
    public ResponseEntity<?> checkDonorEligibility(@PathVariable Integer donorId) {
        return ResponseEntity.status(200).body(vitalSignsService.checkDonorEligibility(donorId));
    }

    @GetMapping("/eligible")
    public ResponseEntity<?> getEligibleDonors() {
        return ResponseEntity.status(200).body(vitalSignsService.getEligibleDonors());
    }

}

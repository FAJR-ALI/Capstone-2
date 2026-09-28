package com.example.capston2blooddonation.Controller;

import com.example.capston2blooddonation.ApiResponse.ApiResponse;
import com.example.capston2blooddonation.Model.Donation;
import com.example.capston2blooddonation.Service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllDonations() {
        return ResponseEntity.status(200).body(donationService.getAllDonations());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDonationById(@PathVariable Integer id) {
        Donation donation = donationService.getDonationById(id);
        if (donation == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Donation not found"));
        }
        return ResponseEntity.status(200).body(donation);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDonation(@RequestBody Donation donation) {
        donationService.addDonation(donation);
        return ResponseEntity.status(200).body(new ApiResponse("Donation Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDonation(@PathVariable Integer id, @RequestBody Donation newDonation){
        Donation updatedDonation = donationService.updateDonation(id, newDonation);

        if (updatedDonation == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Donation not found"));
        }
        return ResponseEntity.status(200).body(updatedDonation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDonation(@PathVariable Integer id) {
        boolean isDeleted = donationService.deleteDonation(id);

        if (!isDeleted) {
            return ResponseEntity.status(404).body(new ApiResponse("Donation not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Donation Deleted Successfully"));
    }

    @GetMapping("/count/{donorId}")
    public ResponseEntity<?> getDonationCount(@PathVariable Integer donorId) {
        long count = donationService.getDonationCount(donorId);

        return ResponseEntity.status(200).body(count);
    }

    @GetMapping("/badge/{donorId}")
    public ResponseEntity<?> getDonorBadge(@PathVariable Integer donorId) {
        String badge = donationService.getDonorBadge(donorId);

        return ResponseEntity.status(200).body(badge);
    }
}

package com.example.capston2blooddonation.Controller;

import com.example.capston2blooddonation.ApiResponse.ApiResponse;
import com.example.capston2blooddonation.Model.Donor;
import com.example.capston2blooddonation.Service.DonorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/donors")
public class DonorController {

    private final DonorService donorService;

    @PostMapping("/add")
    public ResponseEntity<?> addDonor(@Valid @RequestBody Donor donor){
        return ResponseEntity.status(200).body(donorService.addDonor(donor));
    }

    @GetMapping
    public ResponseEntity<?> getAllDonors(){
        return ResponseEntity.status(200).body(donorService.getAllDonors());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDonorById(@PathVariable Integer id){
        Donor donor = donorService.getDonorById(id);
        return ResponseEntity.status(200).body(donor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDonor(@PathVariable Integer id, @Valid @RequestBody Donor donor){
        donorService.updateDonor(id, donor);
        return ResponseEntity.status(200).body(new ApiResponse("Donor Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable Integer id){

        donorService.deleteDonor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Donor Deleted Successfully"));
    }


    @GetMapping("/city/{city}")
    public ResponseEntity<?> getByCity(@PathVariable String city){

        List<Donor> cityDonors = donorService.getDonorByCity(city);

        if(cityDonors.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No donors found in this city"));
        }
        return ResponseEntity.status(200).body(cityDonors);
    }

    @GetMapping("/bloodType/{bloodType}")
    public ResponseEntity<?> getByBloodType(@PathVariable String bloodType){
        List<Donor> bloodTypeDonors = donorService.getDonorByBloodType(bloodType);

        if(bloodTypeDonors.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No Donors found with provided Blood Type "));
        }
        return ResponseEntity.status(200).body(bloodTypeDonors);
    }

    @GetMapping("/blood-type/{bloodType}/city/{city}")
    public ResponseEntity<?> getDonorsByBloodTypeAndCity(@PathVariable String bloodType, @PathVariable String city) {
        return ResponseEntity.status(200).body(donorService.getDonorsByBloodTypeAndCity(bloodType, city));
    }
}

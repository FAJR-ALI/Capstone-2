package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.ApiResponse.ApiException;
import com.example.capston2blooddonation.ApiResponse.ApiResponse;
import com.example.capston2blooddonation.Model.BloodInventory;
import com.example.capston2blooddonation.Model.Donation;
import com.example.capston2blooddonation.Model.Donor;
import com.example.capston2blooddonation.Repository.BloodInventoryRepository;
import com.example.capston2blooddonation.Repository.DonationRepository;
import com.example.capston2blooddonation.Repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final EmailService emailService;
    private final BloodInventoryRepository bloodInventoryRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Integer id) {
        Donation dId = donationRepository.findById(id).orElse(null);
        if(dId == null){
            throw new ApiException("No Dotation with provided Id");
        }
        return dId;
    }

    public Donation addDonation(Donation newDonation) {
        Donor donor = donorRepository.findById(newDonation.getDonor().getId()).orElse(null);

        Donation savedDonation = donationRepository.save(newDonation);

        if (donor != null) {
            BloodInventory inventory = bloodInventoryRepository.findFirstByBloodType(donor.getBloodType());
            if (inventory != null) {
                inventory.setQuantity(inventory.getQuantity() + 1);
                bloodInventoryRepository.save(inventory);
            }
        }
        if (donor != null) {
            emailService.sendEmail(
                    donor.getEmail(),
                    "Blood Donation Accepted",
                    "Thank you for your donation. Your donation has been accepted successfully.");
        }
        return savedDonation;
    }

    public Donation updateDonation(Integer id, Donation newDonation) {
        Donation oldDonation = donationRepository.findById(id).orElse(null);
        if(oldDonation == null){
            throw new ApiException("Cant found the Donation Please check the correct id");
        }
        oldDonation.setDonor(newDonation.getDonor());
        oldDonation.setBloodRequest(newDonation.getBloodRequest());
        return donationRepository.save(oldDonation);
    }

    public void deleteDonation(Integer id) {
        if (!donationRepository.existsById(id)) {
            throw new ApiException("no Donation with provide Id");
        }
        donationRepository.deleteById(id);
    }

    public Long getDonationCount(Integer donorId){
        if (!donorRepository.existsById(donorId)){
            throw new ApiException("Donor not found");
        }
        return donationRepository.countByDonorId(donorId);
    }

    public ApiResponse getDonorBadge(Integer donorId) {
        long count = donationRepository.countByDonorId(donorId);

        if (count >= 6) {
            return new ApiResponse( "Diamond Badge");
        } else if (count >= 4) {
            return new ApiResponse("Gold Badge");
        } else if (count >= 2) {
            return new ApiResponse("Silver Badge");
        } else {
            return new ApiResponse("No Badge");
        }
    }

}

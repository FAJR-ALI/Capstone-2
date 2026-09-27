package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.Model.Donation;
import com.example.capston2blooddonation.Repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Integer id) {
        return donationRepository.findById(id).orElse(null);
    }

    public Donation addDonation(Donation newdonation) {
        return donationRepository.save(newdonation);
    }

    public Donation updateDonation(Integer id, Donation newDonation) {
        Donation oldDonation = donationRepository.findById(id).orElse(null);

        if (oldDonation == null) {
            return null;
        }
        oldDonation.setDonor(newDonation.getDonor());
        oldDonation.setBloodRequest(newDonation.getBloodRequest());
        return donationRepository.save(oldDonation);
    }

    public boolean deleteDonation(Integer id) {
        if (!donationRepository.existsById(id)) {
            return false;
        }
        donationRepository.deleteById(id);
        return true;
    }

    public Long getDonationCount(Integer donorId){
        return donationRepository.countByDonorId(donorId);
    }

    public String getDonorBadge(Integer donorId) {
        long count = donationRepository.countByDonorId(donorId);

        if (count >= 6) {
            return "Diamond";
        } else if (count >= 4) {
            return "Gold";
        } else if (count >= 2) {
            return "Silver";
        } else {
            return "No Badge";
        }
    }

}

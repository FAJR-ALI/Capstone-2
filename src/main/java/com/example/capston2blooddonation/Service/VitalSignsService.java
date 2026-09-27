package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.Model.Donor;
import com.example.capston2blooddonation.Model.VitalSigns;
import com.example.capston2blooddonation.Repository.VitalSignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VitalSignsService {

    private final VitalSignsRepository vitalSignsRepository;

    public List<VitalSigns> getAllVitalSigns() {
        return vitalSignsRepository.findAll();
    }

    public VitalSigns getVitalSignsById(Integer id) {
        return vitalSignsRepository.findById(id).orElse(null);
    }

    public VitalSigns addVitalSigns(VitalSigns vitalSigns) {
        return vitalSignsRepository.save(vitalSigns);
    }

    public VitalSigns updateVitalSigns(Integer id, VitalSigns newVitalSigns) {
        VitalSigns oldVitalSigns =vitalSignsRepository.findById(id).orElse(null);

        if (oldVitalSigns == null) {
            return null;
        }

        oldVitalSigns.setBloodPressure(newVitalSigns.getBloodPressure());
        oldVitalSigns.setHeartRate(newVitalSigns.getHeartRate());
        oldVitalSigns.setTemperature(newVitalSigns.getTemperature());
        oldVitalSigns.setHemoglobinLevel(newVitalSigns.getHemoglobinLevel());
        oldVitalSigns.setBloodSugar(newVitalSigns.getBloodSugar());

        return vitalSignsRepository.save(oldVitalSigns);
    }

    public boolean deleteVitalSigns(Integer id) {

        if (!vitalSignsRepository.existsById(id)) {
            return false;
        }
        vitalSignsRepository.deleteById(id);
        return true;
    }

    public List<VitalSigns> getVitalSignsByDonor(Integer donorId) {
        return vitalSignsRepository.findByDonorId(donorId);
    }

    public String checkDonorEligibility(Integer donorId) {
        VitalSigns vitalSigns =vitalSignsRepository.findTopByDonorIdOrderByIdDesc(donorId);

        if (vitalSigns == null) {
            return "No Vital Signs found for this donor";
        }

        if (vitalSigns.getHeartRate() < 60 ||
                vitalSigns.getHeartRate() > 100) {
            return "Donor is not eligible: Heart rate is not within the normal range";
        }

        if (vitalSigns.getTemperature() < 36 ||
                vitalSigns.getTemperature() > 37.5) {
            return "Donor is not eligible: Temperature is not within the normal range";
        }

        if (vitalSigns.getHemoglobinLevel() < 12.5) {
            return  "Donor is not eligible: Hemoglobin level is too low";
        }

        return "Donor is eligible";
    }

    public List<Donor> getEligibleDonors() {
        return vitalSignsRepository.findEligibleDonors();
    }

}

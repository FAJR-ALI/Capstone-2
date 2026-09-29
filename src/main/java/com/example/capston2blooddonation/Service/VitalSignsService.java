package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.ApiResponse.ApiException;
import com.example.capston2blooddonation.ApiResponse.ApiResponse;
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
        VitalSigns v = vitalSignsRepository.findById(id).orElse(null);
        if(v == null){
            throw new ApiException("No VitalSigns for given Donor id");
        }
        return v;
    }

    public VitalSigns addVitalSigns(VitalSigns vitalSigns) {
        return vitalSignsRepository.save(vitalSigns);
    }

    public VitalSigns updateVitalSigns(Integer id, VitalSigns newVitalSigns) {
        VitalSigns oldVitalSigns =vitalSignsRepository.findById(id).orElse(null);

        if (oldVitalSigns == null) {
            throw new ApiException("no vital Signs to update");
        }

        oldVitalSigns.setBloodPressure(newVitalSigns.getBloodPressure());
        oldVitalSigns.setHeartRate(newVitalSigns.getHeartRate());
        oldVitalSigns.setTemperature(newVitalSigns.getTemperature());
        oldVitalSigns.setHemoglobinLevel(newVitalSigns.getHemoglobinLevel());
        oldVitalSigns.setBloodSugar(newVitalSigns.getBloodSugar());

        return vitalSignsRepository.save(oldVitalSigns);
    }

    public void deleteVitalSigns(Integer id) {
        if (!vitalSignsRepository.existsById(id)) {
           throw new ApiException("no vital Signs to delete with provided id");
        }
        vitalSignsRepository.deleteById(id);

    }

    public List<VitalSigns> getVitalSignsByDonor(Integer donorId) {
        List<VitalSigns> v = vitalSignsRepository.findByDonorId(donorId);
        if(v.isEmpty()){
            throw new ApiException("No vital signs void");
        }
        return vitalSignsRepository.findByDonorId(donorId);
    }

    public ApiResponse checkDonorEligibility(Integer donorId) {
        VitalSigns vitalSigns =vitalSignsRepository.findTopByDonorIdOrderByIdDesc(donorId);
        if (vitalSigns == null) {
            throw new ApiException("No Vital Signs found for this donor");
        }

        if (vitalSigns.getHeartRate() < 60 ||
                vitalSigns.getHeartRate() > 100) {
            return new ApiResponse("Donor is not eligible: Heart rate is not within the normal range");
        }

        if (vitalSigns.getTemperature() < 36 ||
                vitalSigns.getTemperature() > 37.5) {
            return new ApiResponse("Donor is not eligible: Temperature is not within the normal range");
        }

        if (vitalSigns.getHemoglobinLevel() < 12.5) {
            return  new ApiResponse("Donor is not eligible: Hemoglobin level is too low");
        }

        return new ApiResponse("Donor is eligible");
    }

    public List<Donor> getEligibleDonors() {
        List<Donor> donors = vitalSignsRepository.findEligibleDonors();
        if(donors.isEmpty()){
            throw new ApiException("not eligibile donor found");
        }
        return donors;
    }

}

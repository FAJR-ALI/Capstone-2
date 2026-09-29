package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.ApiResponse.ApiException;
import com.example.capston2blooddonation.Model.Donor;
import com.example.capston2blooddonation.Repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public Donor addDonor(Donor newDonor){
        return donorRepository.save(newDonor);
    }

    public List<Donor> getAllDonors(){
        return donorRepository.findAll();
    }

    public Donor getDonorById(Integer id){
        Donor founddonor = donorRepository.findById(id).orElse(null);
        if(founddonor == null){
            throw new ApiException("Donor with provided Id NotFound");
        }
        return founddonor;
    }

    public void updateDonor(Integer id, Donor newDonor){
        Donor oldDonor = donorRepository.findById(id).orElse(null);

        if(oldDonor == null){
            throw new ApiException("Donor not found to be updated");
        }
        oldDonor.setName(newDonor.getName());
        oldDonor.setPhoneNumber(newDonor.getPhoneNumber());
        oldDonor.setEmail(newDonor.getEmail());
        oldDonor.setBloodType(newDonor.getBloodType());
        oldDonor.setAge(newDonor.getAge());
        oldDonor.setCity(newDonor.getCity());
        donorRepository.save(oldDonor);
    }

    public void deleteDonor(Integer id){
        Donor donor = donorRepository.findById(id).orElse(null);
        if(donor == null){
            throw new ApiException("DonorNot Found");
        }
        donorRepository.delete(donor);

    }

    public List<Donor> getDonorByCity(String city){
        List<Donor> donorByCity = donorRepository.findByCity(city);
        if(donorByCity.isEmpty()){
            throw new ApiException("No Donors found in the given City");
        }
        return donorByCity;
    }

    public List<Donor> getDonorByBloodType(String bloodType){
        List<Donor> donorByBloodType = donorRepository.findByBloodType(bloodType);

        if(donorByBloodType.isEmpty()){
            throw new ApiException("No donors found with the given BloodType");
        }
        return donorByBloodType;
    }

    public List<Donor> getDonorsByBloodTypeAndCity(String bloodType, String city) {
        List<Donor> donorBC = donorRepository.findByBloodTypeAndCity(bloodType, city);
        if(donorBC.isEmpty()){
            throw new ApiException("No donor's found with given BloodType and City");
        }
        return donorBC;
    }


}

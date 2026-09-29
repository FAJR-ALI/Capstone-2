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
            throw new ApiException("DonorNot Found");
        }
        return founddonor;
    }

    public void updateDonor(Integer id, Donor newDonor){
        Donor oldDonor = donorRepository.findById(id).orElse(null);

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
        return donorRepository.findByCity(city);
    }

    public List<Donor> getDonorByBloodType(String bloodType){
        return donorRepository.findByBloodType(bloodType);
    }

    public List<Donor> getDonorsByBloodTypeAndCity(String bloodType, String city) {
        return donorRepository.findByBloodTypeAndCity(bloodType, city);
    }


}

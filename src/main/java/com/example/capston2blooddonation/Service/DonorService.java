package com.example.capston2blooddonation.Service;

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
        return donorRepository.findById(id).orElse(null);
    }

    public Donor updateDonor(Integer id, Donor newDonor){
        Donor oldDonor = donorRepository.findById(id).orElse(null);

        if(oldDonor == null){
            return null;
        }

        oldDonor.setName(newDonor.getName());
        oldDonor.setPhoneNumber(newDonor.getPhoneNumber());
        oldDonor.setBloodType(newDonor.getBloodType());
        oldDonor.setAge(newDonor.getAge());
        oldDonor.setCity(newDonor.getCity());
        return donorRepository.save(oldDonor);
    }

    public boolean deleteDonor(Integer id){
        Donor donor = donorRepository.findById(id).orElse(null);

        if(donor == null){
            return false;
        }
        donorRepository.delete(donor);
        return true;
    }

    public List<Donor> getDonorByCity(String city){
        return donorRepository.findByCity(city);
    }

    public List<Donor> getDonorByBloodType(String bloodType){
        return donorRepository.findByBloodType(bloodType);
    }


}

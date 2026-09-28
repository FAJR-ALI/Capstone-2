package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.Model.BloodRequest;
import com.example.capston2blooddonation.Repository.BloodRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;

    public List<BloodRequest> getAllBloodRequest(){
        return bloodRequestRepository.findAll();
    }

    public BloodRequest getBloodRequestById(Integer id){
        return bloodRequestRepository.findById(id).orElse(null);
    }

    public BloodRequest addBloodRequest(BloodRequest bloodRequest){
        return bloodRequestRepository.save(bloodRequest);
    }

    public BloodRequest updateBloodRequest(Integer id, BloodRequest newBloodRequest){

        BloodRequest oldRequest = bloodRequestRepository.findById(id).orElse(null);
        if(oldRequest == null){
            return null;
        }
        oldRequest.setBloodType(newBloodRequest.getBloodType());
        oldRequest.setQuantity(newBloodRequest.getQuantity());
        oldRequest.setCity(newBloodRequest.getCity());

        return bloodRequestRepository.save(oldRequest);
    }

    public boolean deleteBloodRequest(Integer id){
        if(!bloodRequestRepository.existsById(id)){
            return false;
        }
        bloodRequestRepository.deleteById(id);
        return true;
    }

    public List<BloodRequest> getBloodRequestsByCity(String city) {
        return bloodRequestRepository.findByCity(city);
    }

    public List<BloodRequest> getBloodRequestsByBloodType(String bloodType) {
        return bloodRequestRepository.findByBloodType(bloodType);
    }

    public List<BloodRequest> getBloodRequestsByQuantity(Integer quantity) {
        return bloodRequestRepository.findByQuantityGreaterThanEqual(quantity);
    }
}

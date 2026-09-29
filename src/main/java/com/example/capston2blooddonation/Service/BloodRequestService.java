package com.example.capston2blooddonation.Service;

import com.example.capston2blooddonation.ApiResponse.ApiException;
import com.example.capston2blooddonation.Model.BloodRequest;
import com.example.capston2blooddonation.Repository.BloodRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingDeque;

@Service
@RequiredArgsConstructor
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;

    public List<BloodRequest> getAllBloodRequest(){
        return bloodRequestRepository.findAll();
    }

    public BloodRequest getBloodRequestById(Integer id){
        BloodRequest b = bloodRequestRepository.findById(id).orElse(null);
        if(b ==null){
            throw new ApiException("No BloodRequests found ");
        }
        return b;
    }

    public BloodRequest addBloodRequest(BloodRequest bloodRequest){
        return bloodRequestRepository.save(bloodRequest);
    }

    public BloodRequest updateBloodRequest(Integer id, BloodRequest newBloodRequest){

        BloodRequest oldRequest = bloodRequestRepository.findById(id).orElse(null);
        if(oldRequest == null){
            throw new ApiException("BloodRequest not found");
        }
        oldRequest.setBloodType(newBloodRequest.getBloodType());
        oldRequest.setQuantity(newBloodRequest.getQuantity());
        oldRequest.setCity(newBloodRequest.getCity());

        return bloodRequestRepository.save(oldRequest);
    }

    public void deleteBloodRequest(Integer id){
        if(!bloodRequestRepository.existsById(id)){
            throw new ApiException("BloodRequest not found");
        }
        bloodRequestRepository.deleteById(id);
    }

    public List<BloodRequest> getBloodRequestsByCity(String city) {
        List<BloodRequest> li = bloodRequestRepository.findByCity(city);
        if(li.isEmpty()){
            throw new ApiException("No BloodRequest found for the provided city");
        }
        return li;
    }

    public List<BloodRequest> getBloodRequestsByBloodType(String bloodType) {
        List<BloodRequest> ld = bloodRequestRepository.findByBloodType(bloodType);

        if(ld.isEmpty()){
            throw new ApiException("no found");
        }
        return ld;
    }

    public List<BloodRequest> getBloodRequestsByQuantity(Integer quantity) {
        List<BloodRequest> lo = bloodRequestRepository.findByQuantityGreaterThanEqual(quantity);
        if(lo.isEmpty()){
            throw new ApiException("no found");
        }
        return lo;
    }
}

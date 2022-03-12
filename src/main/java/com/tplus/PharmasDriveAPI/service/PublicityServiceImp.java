package com.tplus.PharmasDriveAPI.service;

import java.util.Optional;

import com.tplus.PharmasDriveAPI.model.Publicity;
import com.tplus.PharmasDriveAPI.repository.PublicityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class PublicityServiceImp  implements PublicityService{


    @Autowired
    private PublicityRepository publicityRepository ;

    @Override
    public Page<Publicity> getPublicities(Pageable pageable) {
        return publicityRepository.findAll(pageable);
        
    }

    @Override
    public Publicity addPublicity(Publicity publicity) {
        return publicityRepository.save(publicity);
        
    }

    @Override
    public Publicity getPublicity(String id) {
        Optional<Publicity> publicity =  publicityRepository.findById(id);
        if (publicity.isPresent())
            return publicity.get();
        throw new RuntimeException("Donation is not found for the id "+ id);
		
    }

    @Override
    public void deletePublicity(String id){
        publicityRepository.deleteById(id);
    }

    @Override
    public Publicity updatePublicity(Publicity publicity) {
        return publicityRepository.save(publicity);
    }
}

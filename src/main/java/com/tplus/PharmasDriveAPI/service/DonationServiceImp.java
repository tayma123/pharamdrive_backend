package com.tplus.PharmasDriveAPI.service;

import java.util.Optional;

import com.tplus.PharmasDriveAPI.model.Donation;
import com.tplus.PharmasDriveAPI.repository.DonationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class DonationServiceImp  implements DonationService{


    @Autowired
    private DonationRepository donationRepository ;

    @Override
    public Page<Donation> getDonations(Pageable pageable) {
        return donationRepository.findAll(pageable);
        
    }

    @Override
    public Donation addDonation(Donation donation) {
        return donationRepository.save(donation);
        
    }

    @Override
    public Donation getDonation(String id) {
        Optional<Donation> donation =  donationRepository.findById(id);
        if (donation.isPresent())
            return donation.get();
        throw new RuntimeException("Donation is not found for the id "+ id);
		
    }

    @Override
    public void deleteDonation(String id){
        //donationRepository.deleteById(id);
    }

    @Override
    public Donation updateDonation(Donation donation) {
        return donationRepository.save(donation);
    }
}
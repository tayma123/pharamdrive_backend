package com.tplus.PharmasDriveAPI.service;

import com.tplus.PharmasDriveAPI.model.Donation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationService {

    Page<Donation> getDonations(Pageable pageable);
    Donation addDonation(Donation donation);
    Donation  getDonation(String id );
    void deleteDonation(String id );
    Donation updateDonation(Donation donation);
}

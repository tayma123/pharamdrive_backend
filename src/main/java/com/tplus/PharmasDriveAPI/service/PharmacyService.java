package com.tplus.PharmasDriveAPI.service;

import com.tplus.PharmasDriveAPI.model.Pharmacy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface PharmacyService {
	
    Page<Pharmacy> getPharmacies(Pageable pageable);
	
    Pharmacy createPharmacy(Pharmacy pharmacy );
	
	Pharmacy getPharmacy(String id);
	
	void deletePharmacy(String id);
	
	Pharmacy updatePharmacy(Pharmacy pharmacy);

    Integer countOrders();

}

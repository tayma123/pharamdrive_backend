package com.tplus.PharmasDriveAPI.service;

import com.tplus.PharmasDriveAPI.model.Pharmacy;
import com.tplus.PharmasDriveAPI.repository.PharmacyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class PharmacyServiceImp  implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository ; 

	@Override
	public Pharmacy createPharmacy(Pharmacy pharmacy) {
		
		return pharmacyRepository.save(pharmacy);
	}

	@Override
	public Pharmacy getPharmacy(String id) {
		
		Optional<Pharmacy> pharmacy =  pharmacyRepository.findById(id);
		if (pharmacy.isPresent())
			return pharmacy.get();
		
		throw new RuntimeException("Pharmacy is not found for the id "+ id);
	}

	@Override
	public void deletePharmacy(String id) {
		pharmacyRepository.deleteById(id);
		
	}

	@Override
	public Pharmacy updatePharmacy(Pharmacy pharmacy) {
		
		return pharmacyRepository.save(pharmacy);
	}

	@Override
	public Page<Pharmacy> getPharmacies(org.springframework.data.domain.Pageable pageable) {
		return pharmacyRepository.findAll(pageable);
	}

	@Override
	public Integer countOrders() {
		return (int) pharmacyRepository.count() ;
	}


}
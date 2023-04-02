package com.pharamdrive.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.pharamdrive.RessourcesDto.MedicmentAvecBasPrixDto;
import com.pharamdrive.models.Medicament;
import com.pharamdrive.repository.MedicamentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.models.Users;
import com.pharamdrive.repository.PharmacieRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class PharmacieController {
	@Autowired
	public PharmacieRepository pharmRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	MedicamentsRepository medicamentsRepository;
	@PostMapping(value="/new/pharmacie")
	public Pharmacie addPharmacie(@RequestBody Pharmacie pharam) {
		return pharmRepo.save(pharam);
	}

	 @PostMapping("/login")
	    public Pharmacie login(@RequestBody Pharmacie pharmacie){

	        Pharmacie OldUser = pharmRepo.findByEmailAndPassword(pharmacie.getEmail(),pharmacie.getPassword());
	        return  OldUser;
	    }

	@GetMapping(value="/pharmacies")
	public List<Pharmacie> getPharmacies(){
		return pharmRepo.findAll();
	}

	@PostMapping(value ="/pharmacie/{id}")
	public Optional<Pharmacie> getPharmacieId(@PathVariable  String id ){
		return  pharmRepo.findById(id);

	}
	@GetMapping(value="/near/{longitude}/{latitude}/{maxDistanceInKm}")

	public List<Pharmacie> findNearestPharmacies(@PathVariable double longitude, @PathVariable double latitude,@PathVariable double maxDistanceInKm) {
		Point location = new Point(longitude, latitude);
		Distance maxDistance = new Distance(maxDistanceInKm, Metrics.KILOMETERS);
		Circle circle = new Circle(location, maxDistance);
		Query query = Query.query(Criteria.where("location").withinSphere(circle));

		List<Pharmacie> nearestPharmacies = mongoTemplate.find(query, Pharmacie.class);
		return nearestPharmacies;
	}

	 @PostMapping(value ="/addLocationToPharmacie")
	 public void addLocationToPharmacie( ){
		List<Pharmacie> all=  pharmRepo.findAll();
		 for (int i = 0; i < all.size(); i++) {

			 if (all.get(i).getLongitude() != null && all.get(i).getAltitude() != null) {
				 Point locations = new Point(all.get(i).getLongitude(), all.get(i).getAltitude());
				 all.get(i).setLocation(locations);
				 pharmRepo.save(all.get(i));
			 }
		 }
	 }
	@GetMapping(value="/medicamentAvecBasPrix/{longitude}/{latitude}/{maxDistanceInKm}/{nomdDeMedicmaent}")

	public List<MedicmentAvecBasPrixDto> medicamentAvecBasPrix(@PathVariable double longitude, @PathVariable double latitude,@PathVariable double maxDistanceInKm,@PathVariable String nomdDeMedicmaent) {
		Point location = new Point(longitude, latitude);
		Distance maxDistance = new Distance(maxDistanceInKm, Metrics.KILOMETERS);
		Circle circle = new Circle(location, maxDistance);
		Query query = Query.query(Criteria.where("location").withinSphere(circle));
       List<MedicmentAvecBasPrixDto> medi=new ArrayList<>();

		List<Pharmacie> nearestPharmacies = mongoTemplate.find(query, Pharmacie.class);
		for(int i=0;i<nearestPharmacies.size();i++){
			Optional<Medicament> medicament= medicamentsRepository.findByIdPharmacieAndNomMedicament(nearestPharmacies.get(i).getId_pharmacie(),nomdDeMedicmaent);
			MedicmentAvecBasPrixDto medicmentAvecBasPrixDto=new MedicmentAvecBasPrixDto();
			if(medicament.isPresent()){

				medicmentAvecBasPrixDto.setPrix(medicament.get().getPrix());
				medicmentAvecBasPrixDto.setIdMedicament(medicament.get().getId_medicament());
				medicmentAvecBasPrixDto.setNomMedicament(medicament.get().getNomMedicament());
				medicmentAvecBasPrixDto.setNomPharmacie(nearestPharmacies.get(i).getName());
				medicmentAvecBasPrixDto.setIdPharmacie(nearestPharmacies.get(i).getId_pharmacie());

			}
			medi.add(medicmentAvecBasPrixDto);

		}

		return medi;
	}
}

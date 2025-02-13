package com.pharamdrive.controller;

import java.awt.geom.Point2D;
import java.util.*;

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
import org.springframework.web.bind.annotation.*;

import com.pharamdrive.models.Pharmacie;

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

    @PostMapping(value = "/new/pharmacie")
    public Pharmacie addPharmacie(@RequestBody Pharmacie pharam) {
        return pharmRepo.save(pharam);
    }

    @PostMapping(value = "/update/pharmacie")
    public Pharmacie updatePharmacie(@RequestBody Pharmacie pharam) {

        return pharmRepo.save(pharam);
    }

    @PostMapping("/login")
    public Pharmacie login(@RequestBody Pharmacie pharmacie) {

        Pharmacie OldUser = pharmRepo.findByEmailAndPassword(pharmacie.getEmail(), pharmacie.getPassword());
        return OldUser;
    }

    @GetMapping(value = "/pharmacies")
    public List<Pharmacie> getPharmacies() {
        return pharmRepo.findAll();
    }

    @GetMapping(value = "/pharmacie/{id}")
    public Optional<Pharmacie> getPharmacieId(@PathVariable String id) {
        return pharmRepo.findById(id);

    }

    @GetMapping(value = "/near/{longitude}/{latitude}/{maxDistanceInKm}")

    public List<Pharmacie> findNearestPharmacies(@PathVariable double longitude, @PathVariable double latitude, @PathVariable double maxDistanceInKm) {
        Point location = new Point(longitude, latitude);
        Distance maxDistance = new Distance(maxDistanceInKm, Metrics.KILOMETERS);
        Circle circle = new Circle(location, maxDistance);
        Query query = Query.query(Criteria.where("location").withinSphere(circle));

        List<Pharmacie> nearestPharmacies = mongoTemplate.find(query, Pharmacie.class);
        return nearestPharmacies;
    }

    @PostMapping(value = "/addLocationToPharmacie")
    public void addLocationToPharmacie() {
        List<Pharmacie> all = pharmRepo.findAll();
        for (int i = 0; i < all.size(); i++) {

            if (all.get(i).getLongitude() != null && all.get(i).getAltitude() != null) {
                Point locations = new Point(all.get(i).getLongitude(), all.get(i).getAltitude());
                all.get(i).setLocation(locations);
                pharmRepo.save(all.get(i));
            }
        }
    }

    @GetMapping(value = "/medicamentAvecBasPrix/{longitude}/{latitude}/{maxDistanceInKm}/{nomdDeMedicmaent}")
    public List<MedicmentAvecBasPrixDto> medicamentAvecBasPrix(@PathVariable double longitude, @PathVariable double latitude, @PathVariable double maxDistanceInKm, @PathVariable String nomdDeMedicmaent) {
        Point location = new Point(longitude, latitude);
        Distance maxDistance = new Distance(maxDistanceInKm, Metrics.KILOMETERS);
        Circle circle = new Circle(location, maxDistance);
        Query query = Query.query(Criteria.where("location").withinSphere(circle));
        List<MedicmentAvecBasPrixDto> medi = new ArrayList<>();
        List<Pharmacie> nearestPharmacies = mongoTemplate.find(query, Pharmacie.class);
        for (int i = 0; i < nearestPharmacies.size(); i++) {
            double distance =   calculateDistance(latitude,longitude,nearestPharmacies.get(i).getAltitude(),nearestPharmacies.get(i).getLongitude());
            Optional<Medicament> medicament = medicamentsRepository.findByIdPharmacieAndNomMedicament(nearestPharmacies.get(i).getId_pharmacie(), nomdDeMedicmaent);
            MedicmentAvecBasPrixDto medicmentAvecBasPrixDto = new MedicmentAvecBasPrixDto();
            if (medicament.isPresent()) {
                medicmentAvecBasPrixDto.setPrix(medicament.get().getPrix());
                medicmentAvecBasPrixDto.setIdMedicament(medicament.get().getId_medicament());
                medicmentAvecBasPrixDto.setNomMedicament(medicament.get().getNomMedicament());
                medicmentAvecBasPrixDto.setNomPharmacie(nearestPharmacies.get(i).getName());
                medicmentAvecBasPrixDto.setIdPharmacie(nearestPharmacies.get(i).getId_pharmacie());
                medicmentAvecBasPrixDto.setDistance(distance);
                medi.add(medicmentAvecBasPrixDto);

            }

        }
        Collections.sort(medi, Comparator.comparingDouble(MedicmentAvecBasPrixDto::getPrice));

        return medi;
    }

    double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double lon1Rad = Math.toRadians(lon1);
        double lon2Rad = Math.toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);
        double distance = Math.sqrt(x * x + y * y) * 6371000;

        return distance;
    }

    //
    @PutMapping("/updateLongitudeAndAltitude/{idpharmacie}/{longitude}/{altitude}")
    public Pharmacie updateLongitudeAndAltitude(@PathVariable String idpharmacie, @PathVariable double longitude, @PathVariable double altitude) {

        Pharmacie pharmacie = pharmRepo.findById(idpharmacie).get();
        pharmacie.setLongitude(longitude);
        pharmacie.setAltitude(altitude);
        Point location = new Point(longitude, altitude);
        pharmacie.setLocation(location);
        pharmRepo.save(pharmacie);
        return pharmacie;
    }

    @GetMapping(value = "/pharmacie/search/{mot}")
    public List<Pharmacie> search(@PathVariable(value = "mot") String mot) {
        List<Pharmacie> pharmacies = pharmRepo.findAll();
        System.out.println("________________" + pharmacies.size());
        List<Pharmacie> newlist = new ArrayList<>();
        for (int i = 0; i < pharmacies.size(); i++) {
            if (pharmacies.get(i).getName().toLowerCase().contains(mot.toLowerCase())) {
                newlist.add(pharmacies.get(i));
            }
        }
        return newlist;

    }

    @GetMapping(value = "/pharmacie/quartier/{mot}")
    public List<Pharmacie> searchbyQuartier(@PathVariable(value = "mot") String mot) {
        return pharmRepo.findByDepartementContainingIgnoreCase(mot);
    }
    @GetMapping(value = "/pharmacie/zipCode/{zipCode}")
    public List<Pharmacie> searchbyZipCode(@PathVariable(value = "zipCode") String zipCode) {

        List<Pharmacie> pharmacies = pharmRepo.findAll();
        List<Pharmacie> pharmaciesfilter = new ArrayList<>();
        for (int i = 0; i < pharmacies.size(); i++) {
            if(pharmacies.get(i).getZipCode().startsWith(zipCode)){
                pharmaciesfilter.add(pharmacies.get(i));
            }

        }
     return pharmaciesfilter;
    }    @GetMapping(value = "/pharmacie/adresse/{adresse}")
    public List<Pharmacie> searchbyAdresse(@PathVariable(value = "adresse") String adresse) {
        return pharmRepo.findByAdresseContainingIgnoreCase(adresse);
    }

    @GetMapping(value = "/correctifZipCode")
    public void correctifZipCode() {
        List<Pharmacie> pharmacies=pharmRepo.findAll();
        for(int i=0;i<pharmacies.size();i++){
            pharmacies.get(i).setZipCode(pharmacies.get(i).getAdresse().substring(0,5));
            pharmRepo.save(pharmacies.get(i));
        }

    }
    @GetMapping(value = "/correctifName")
    public void correctifVilleName() {
        List<Pharmacie> pharmacies=pharmRepo.findAll();
        for(int i=0;i<pharmacies.size();i++){
            pharmacies.get(i).setVilleName(pharmacies.get(i).getAdresse().substring(6,pharmacies.get(i).getAdresse().length()));
            pharmRepo.save(pharmacies.get(i));
        }
    }
    @GetMapping(value = "/suggestedList/{mot}")
    public List<String> getSuggestedList(@PathVariable(value = "mot") String mot) {
        List<Pharmacie> pharmacies=pharmRepo.findAll();
        List<String> addresses=new ArrayList<>();
        for(int i=0;i<pharmacies.size();i++){
            if(pharmacies.get(i).getAdresse().startsWith(mot)){
                addresses.add(pharmacies.get(i).getAdresse());
            }

        }
        return addresses;
    }
    @GetMapping(value = "/pharmacWithLessPrice/{noun}/{max}/{min}/{altitude}/{longitude}/{maxDist}")
    public List<Medicament> getMedicamentWithLessPrice(@PathVariable(value = "noun") String noun,@PathVariable(value = "max") Double max,@PathVariable(value = "min") Double min,@PathVariable(value = "altitude") Double altitude,@PathVariable(value = "longitude") Double longitude,@PathVariable(value = "maxDist") Double maxDist) {
        List<Pharmacie> allpharmacies = findNearestPharmacies(longitude,altitude,maxDist);
        List<Medicament> allMedicaments =new ArrayList<>();
        for(int i=0;i<allpharmacies.size();i++){
            allMedicaments.addAll(medicamentsRepository.findAllByIdPharmacie(allpharmacies.get(i).getId_pharmacie())); // Une liste de tous les médicaments
        }
        System.out.println("_______________"+allMedicaments.size());
        List<Medicament> medicamentsFiltered = new ArrayList<>();

        // Parcourir tous les médicaments pour vérifier leur prix
        for (Medicament medicament : allMedicaments) {
            if(medicament.getNomMedicament().equals(noun)){
            String price = medicament.getPrix();

            price=price.substring(0,price.length()-1);
            if(!price.isEmpty()){
            double priceDouble=Double.parseDouble(price);

            System.out.println("_______________"+priceDouble+"_______________");

            // Vérifier si le prix du médicament est dans la plage spécifiée
            if ((priceDouble >= min) && (priceDouble <= max)) {
                System.out.println("_______________"+medicament);
                medicamentsFiltered.add(medicament);
            }}
        }}
        return medicamentsFiltered;
    }
//    @GetMapping(value = "/pharmacWithLessPrice/{max}/{min}")
//    public List<Medicament> getPharmacWithLessPrice(@PathVariable(value = "max") Double max,@PathVariable(value = "min") Double min) {
//        List<Medicament> allMedicaments = medicamentsRepository.findAll(); // Une liste de tous les médicaments
//        System.out.println("_______________"+allMedicaments.size());
//        List<Medicament> medicamentsFiltered = new ArrayList<>();
//
//        // Parcourir tous les médicaments pour vérifier leur prix
//        for (Medicament medicament : allMedicaments) {
//            String price = medicament.getPrix();
//
//            price=price.substring(0,price.length()-1);
//            double priceDouble=Double.parseDouble(price);
//
//            System.out.println("_______________"+priceDouble+"_______________");
//
//            // Vérifier si le prix du médicament est dans la plage spécifiée
//            if ((priceDouble >= min) && (priceDouble <= max)) {
//                System.out.println("_______________"+medicament);
//                medicamentsFiltered.add(medicament);
//            }
//        }
//        return medicamentsFiltered;
//    }
// Fonction utilitaire pour déterminer si un input est un code postal
private boolean isZipCode(String input) {
    return input.matches("\\d+");
}

    // Fonction pour valider l'adresse ou la ville
    private boolean isAddressOrCity(String input) {
        if (!isZipCode(input) &&((input.matches("[a-zA-Z]+")||input.matches("[a-zA-Z0-9]+")) )){
            return true;

        }
        else
            return false;

    }

    // Recherche par un critère (zipCode, adresse, ou ville)
    @GetMapping("/find/{mot}")
    public List<Pharmacie> searchPharmacies(@PathVariable(name = "mot") String mot) {
        if (isZipCode(mot)) {

            List<Pharmacie> pharmacies = pharmRepo.findAll();
            List<Pharmacie> pharmaciesfilter = new ArrayList<>();
            for (int i = 0; i < pharmacies.size(); i++) {
                if(pharmacies.get(i).getZipCode().startsWith(mot)) {
                     pharmaciesfilter.add(pharmacies.get(i));
                }}
            return pharmaciesfilter;// Recherche par code postal
        } else  {
            // Recherche par adresse ou ville
            List<Pharmacie> pharmaciesByAdresse = pharmRepo.findByAdresseContainingIgnoreCase(mot);
            List<Pharmacie> pharmaciesByVille = pharmRepo.findByVilleNameContainingIgnoreCase(mot);
            System.out.println("______________________"+pharmaciesByVille.size());
            if(pharmaciesByAdresse!=null){
                pharmaciesByAdresse.addAll(pharmaciesByVille);}
            else {
                pharmaciesByAdresse=new ArrayList<>();
                pharmaciesByAdresse.addAll(pharmaciesByVille);}

            // Optionnellement, éliminer les doublons
            System.out.println("______________________"+pharmaciesByAdresse);
            return pharmaciesByAdresse.stream().distinct().toList();
        } 
    }
}

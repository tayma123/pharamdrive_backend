package com.pharamdrive.controller;

import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.repository.PharmacieRepository;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PharmacyController {
private final PharmacieRepository pharmacieRepository;

    public PharmacyController(PharmacieRepository pharmacieRepository) {
        this.pharmacieRepository = pharmacieRepository;
    }

    @GetMapping("/pharmacieskkkk")
    public List<Pharmacie> getPharmacies() {
        // List of pharmacies with addresses
        List<Pharmacie> pharmacies = pharmacieRepository.findAll();

        // Iterate through pharmacies and retrieve longitude and latitude
        for (Pharmacie pharmacy : pharmacies) {

           String address= pharmacy.getAdresse().concat(" ").concat(pharmacy.getRue());
            System.out.println(address);
            String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + address;

            RestTemplate restTemplate = new RestTemplate();
            OSMGeocodingResponse[] responses = restTemplate.getForObject(url, OSMGeocodingResponse[].class);

            if (responses != null && responses.length > 0) {
                OSMGeocodingResponse response = responses[0];
                pharmacy.setAltitude(response.getLat());
                pharmacy.setLongitude(response.getLon());
                Point location=new Point(response.getLon(),response.getLat());
                pharmacy.setLocation(location);
                pharmacieRepository.save(pharmacy);
                System.out.println(pharmacy.getId_pharmacie());

            } else {
                System.out.println("erroooooooooor");
            }
        }

        return pharmacies;
    }


}

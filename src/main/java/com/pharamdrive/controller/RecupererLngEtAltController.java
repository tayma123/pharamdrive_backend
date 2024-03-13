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
@RequestMapping("/pharm")
public class RecupererLngEtAltController {
    final PharmacieRepository pharmacieRepository;


    private final String GOOGLE_MAPS_API_KEY = "AIzaSyCTwKWuemVpYsmYv2seF7MQJCWnWisSF9s";

    public RecupererLngEtAltController(PharmacieRepository pharmacieRepository) {
        this.pharmacieRepository = pharmacieRepository;
    }

    @GetMapping("/pharmacieslongetaltitude")
    public List<Pharmacie> getPharmacies() {
        // List of pharmacies with addresses
        List<Pharmacie> pharmacies = pharmacieRepository.findAll();

        // Iterate through pharmacies and retrieve longitude and latitude
        for (Pharmacie pharmacy : pharmacies) {
            String address = pharmacy.getAdresse();
            System.out.println("_______________"+address);
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + GOOGLE_MAPS_API_KEY;

            RestTemplate restTemplate = new RestTemplate();
            GoogleGeocodingResponse response = restTemplate.getForObject(url, GoogleGeocodingResponse.class);
            System.out.println("_______________"+response);
            if (response != null && response.getStatus().equals("OK")) {
                GoogleGeocodingResponse.Result result = response.getResults().get(0);
                pharmacy.setAltitude(result.getGeometry().getLocation().getLat());
                pharmacy.setLongitude(result.getGeometry().getLocation().getLng());
                Point location=new Point(result.getGeometry().getLocation().getLng(),result.getGeometry().getLocation().getLat());

                pharmacy.setLocation(location);
                pharmacieRepository.save(pharmacy);
                System.out.println(pharmacy.getId_pharmacie());

            } else {
                System.out.println("error");
            }
        }

        return pharmacies;
    }





}

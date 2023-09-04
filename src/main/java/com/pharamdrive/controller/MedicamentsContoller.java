package com.pharamdrive.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pharamdrive.models.Notification;
import com.pharamdrive.models.Pharmacie;
import com.pharamdrive.models.Promotion;
import com.pharamdrive.repository.NotificationRepository;
import com.pharamdrive.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.pharamdrive.models.Medicament;
import com.pharamdrive.repository.MedicamentsRepository;
import com.pharamdrive.repository.PharmacieRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")

public class MedicamentsContoller {
    @Autowired
    public MedicamentsRepository MedicamentsRepo;
    @Autowired
    public final PharmacieRepository pharmRepo;
    @Autowired
    public final PromotionRepository promotionRepository;
    @Autowired
    public final NotificationRepository notificationRepository;

    public MedicamentsContoller(PharmacieRepository pharmRepo, PromotionRepository promotionRepository, NotificationRepository notificationRepository) {
        this.pharmRepo = pharmRepo;
        this.promotionRepository = promotionRepository;
        this.notificationRepository = notificationRepository;
    }


    //add medicament under pharmacy
    @PostMapping(value = "/addMedicamentUnderPharmacy/{idPharmacy}")
    public String addmedicamentUnderPharmacy(@RequestBody Medicament med, @PathVariable String idPharmacy) {
        med.setIdPharmacie(idPharmacy);
       med= MedicamentsRepo.save(med);
       System.out.println("______________________________"+med);


        return "medicaments added successfully";
    }

    //update medicament under pharmacy
    @PostMapping(value = "/updateMedicamentUnderPharmacy}")
    public String updatemedicamentUnderPharmacy(@RequestBody Medicament med) {

        MedicamentsRepo.save(med);


        return "medicament update successfully";
    }
    //delete medicament under pharmacy
    @DeleteMapping(value = "/deleteMedicament/{id}")
    public String deleteMedicament(@PathVariable(value = "id") String id) {
        MedicamentsRepo.deleteById(id);
        return "medicament deleted successfully";
    }





    //get all medicaments
    @GetMapping("/medicaments")
    public List<Medicament> getAllMedicaments() {
        return MedicamentsRepo.findAll();
    }

    //add medicament
    @PostMapping(value = "/addMedicament")
    public String addMEdicaments(@RequestBody Medicament med) {

        MedicamentsRepo.save(med);
        return "medicaments added successfully";
    }
    //add promotion to medicament
    @PostMapping(value = "/addPromotionToMedicament/{idMedicament}")
    public String addPromotionToMedicament(@RequestBody String promotion,@PathVariable(value = "idMedicament") String idMedicament) {

        Medicament medicament=getMedicament(idMedicament);
        Promotion promotion1= new Promotion();
        promotion1.setPromotion(promotion);
        promotion1=promotionRepository.save(promotion1);
        medicament.setIdPromotion(promotion1.getIdPromotion());
        MedicamentsRepo.save(medicament);
        return "promotion added successfully";
    }
    //delete promotion to medicament
    @PostMapping(value = "/deletePromotionToMedicament/{idMedicament}")
    public String deletePromotionfromMedicament(@PathVariable(value = "idMedicament") String idMedicament) {
        Medicament medicament=getMedicament(idMedicament);
        promotionRepository.deleteById(medicament.getIdPromotion());
        medicament.setIdPromotion(null);
        MedicamentsRepo.save(medicament);
        return "promotion deleted successfully";
    }
    @Scheduled(cron = "* * * * *")
    public void checkQuantityBeforeAttentSeuil() {
   List<Medicament> medicaments=getAllMedicaments();
        for(int i=0;i<medicaments.size();i++ ){
            if(medicaments.get(i).getSeuil()!=null &&medicaments.get(i).getQuantite()!=null &&medicaments.get(i).getAlerteForSeuil()!=null){
            if(medicaments.get(i).getSeuil()- medicaments.get(i).getQuantite()==medicaments.get(i).getAlerteForSeuil()){
                Pharmacie pharmacie=pharmRepo.findById(medicaments.get(i).getIdPharmacie()).get();
                List<Notification> notifications=new ArrayList<>();
                if(pharmacie.getNotifications()!=null){
                    notifications=pharmacie.getNotifications();
                }
                Notification notification=new Notification();
                notification.setText("Vous avez presque attenué votre seuil pour la quantité de medicament pour le   "+medicaments.get(i).getNomMedicament());
                notification=notificationRepository.save(notification);
                notifications.add(notification);
                pharmacie.setNotifications(notifications);
                pharmRepo.save(pharmacie);
            }}
        }

    }
    @Scheduled(cron = "* * * * *")
    public void checkSeuil() {
        List<Medicament> medicaments=getAllMedicaments();
        for(int i=0;i<medicaments.size();i++ ){
            if(medicaments.get(i).getSeuil()!=null &&medicaments.get(i).getQuantite()!=null){
                if(medicaments.get(i).getSeuil()==medicaments.get(i).getQuantite()){
                    Pharmacie pharmacie=pharmRepo.findById(medicaments.get(i).getIdPharmacie()).get();
                    List<Notification> notifications=new ArrayList<>();
                    if(pharmacie.getNotifications()!=null){
                        notifications=pharmacie.getNotifications();
                    }
                    Notification notification=new Notification();
                    notification.setText("Attention Vous avez attenué votre seuil pour la quantité de medicament pour le  "+medicaments.get(i).getNomMedicament());
                    notification=notificationRepository.save(notification);
                    notifications.add(notification);
                    pharmacie.setNotifications(notifications);
                    pharmRepo.save(pharmacie);
                }}
        }

    }
    @PutMapping(value = "/addStockToMedicament/{idMedicament}")

    Medicament addStockToMedicament(@PathVariable(value = "idMedicament")  String idMedicament,@RequestBody Integer qunatite){
        Medicament medicament=getMedicament(idMedicament);
        medicament.setQuantite(qunatite);
        updatemedicamentUnderPharmacy(medicament);
        return medicament;
    }
    @GetMapping("/medicaments/pharmacy/{id}")
    public List<Medicament> getAllMedicamentPharmacys(@PathVariable String id) {
        return MedicamentsRepo.findAllByIdPharmacie(id);
    }
    //Api get  medicament
    @GetMapping(value = "/medicament/{id}")
    public Medicament getMedicament(@PathVariable(value = "id") String id) {

        return MedicamentsRepo.findById(id).get();
    }
    @PutMapping(value = "/addTracabiliteToMedicament/{idMedicament}")

        Medicament addTracabiliteToMedicament(@PathVariable(value = "idMedicament")  String idMedicament,@RequestBody String tracabilite){
        Medicament medicament=getMedicament(idMedicament);
        medicament.setTracabilite(tracabilite);
        updatemedicamentUnderPharmacy(medicament);
        return medicament;
    }
}

package com.tplus.PharmasDriveAPI.repository;


import com.tplus.PharmasDriveAPI.model.Pharmacy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PharmacyRepository extends MongoRepository<Pharmacy, String> {


    //public  List<Pharmacy> findAll( Pageable pageable);
   

    


}
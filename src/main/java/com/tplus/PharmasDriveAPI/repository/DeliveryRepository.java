
package com.tplus.PharmasDriveAPI.repository;

import com.tplus.PharmasDriveAPI.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

}


package com.tplus.PharmasDriveAPI.repository;

import com.tplus.PharmasDriveAPI.model.Order;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query(value ="{status:?0}", count = true)
    Long countFindByStatus(String status);

    

}

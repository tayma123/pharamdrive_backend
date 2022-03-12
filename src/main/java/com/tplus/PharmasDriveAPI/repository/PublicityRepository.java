
package com.tplus.PharmasDriveAPI.repository;

import com.tplus.PharmasDriveAPI.model.Publicity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PublicityRepository extends MongoRepository<Publicity, String> {

}



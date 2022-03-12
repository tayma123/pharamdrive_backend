package com.tplus.PharmasDriveAPI.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.tplus.PharmasDriveAPI.model.User;


/*@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = " select * from tbl_user u where u.email = ?1 and u.password = ?2" , nativeQuery = true)
    User findByEmailAndPassword(String email , String password) ;

}*/

@Repository
public interface UserRepository extends MongoRepository<User , String>{


    @Query("{$and:[{email:?0},{password:?1}]}")
    public User login(String email , String password);



    public boolean existsByEmail(String email);

    @Query("select u.password from User u where u.email=?1")
    public String getPasswordByEmail(String email);



    public User findByEmail(String email);
 
	
}

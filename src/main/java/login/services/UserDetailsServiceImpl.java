//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package login.services;
import com.pharamdrive.models.User;
import login.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private final MongoTemplate mongoTemplate;
	@Autowired
	UserLoginRepository userLoginRepository;

	public UserDetailsServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userLoginRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("user not sedreeztr  found");
		} else {
			System.out.println("hereeeeee3");
			return UserDetailsImpl.build(user);
		}
	}

	public UserDetails loadUserByUserID(String userID) throws UsernameNotFoundException {
		Query query = new Query();
		query.fields().include("username").include("id").include("login").include("verificationId").include("password");
		query.addCriteria(Criteria.where("id").is(userID));
		User user = (User)this.mongoTemplate.findOne(query, User.class);
		System.out.println("i'm heere2");
		if (user == null) {
			throw new RuntimeException("user not sedreeztr  found");
		} else {
			return UserDetailsImpl.build(user);
		}
	}
}

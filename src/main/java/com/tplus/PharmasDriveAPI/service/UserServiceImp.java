package com.tplus.PharmasDriveAPI.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tplus.PharmasDriveAPI.model.User;
import com.tplus.PharmasDriveAPI.repository.UserRepository;


@Service
public class UserServiceImp implements UserService    {
	

	@Autowired
	private UserRepository userRepository ;

	@Override
	public List<User> getUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User registerUser(User user) {
		
		return userRepository.insert(user);
	}

	@Override
	public User getUser(String id) {
		Optional<User> user =  userRepository.findById(id);
		if (user.isPresent())
			return user.get();
		
		throw new RuntimeException("User is not found for the id "+ id);
	}

	@Override
	public void deleteUser(String id) {
		
		userRepository.deleteById(id);
		
	}

	@Override
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

	/*@Override
	public User loginUser(User user) {

		
		 return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
	}*/
	
	
	@Override
	public User loginUser(User user) {

		
		 return userRepository.login(user.getEmail()
		 , user.getPassword());
	}

	@Override
	public boolean ifEmailExist(String email) {
		
		return userRepository.existsByEmail(email);
	}

	@Override
	public User getUserBuMail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public void editUser(User user) {
		this.userRepository.save(user);
		
	}
	
	

}



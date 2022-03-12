package com.tplus.PharmasDriveAPI.service;

import java.util.List;

import com.tplus.PharmasDriveAPI.model.User;

public interface UserService {
	
	List<User> getUsers();
	
	User registerUser(User user );
	
	User getUser(String id);
	
	void deleteUser(String id);
	
	User updateUser(User user);

	User loginUser(User user);

	boolean ifEmailExist(String email);

    User getUserBuMail(String email);

    void editUser(User user);

}

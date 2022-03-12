package com.tplus.PharmasDriveAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tplus.PharmasDriveAPI.model.ResetPassword;
import com.tplus.PharmasDriveAPI.model.AccountResponse;
import com.tplus.PharmasDriveAPI.model.Mail;
import com.tplus.PharmasDriveAPI.model.NewPassword;
import com.tplus.PharmasDriveAPI.model.User;
import com.tplus.PharmasDriveAPI.model.UserCode;
import com.tplus.PharmasDriveAPI.service.EmailService;
import com.tplus.PharmasDriveAPI.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService uService;
	@Autowired
	private EmailService emailService ;

	
	
	
	@GetMapping( "/users")

	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(uService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		
		return new ResponseEntity<User>(uService.getUser(id), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteUser(@RequestParam String id) {
		
		uService.deleteUser(id);
		return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	

	}
    @CrossOrigin
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		return new ResponseEntity<User>(uService.registerUser(user), HttpStatus.CREATED);
	}


	@CrossOrigin
	@PostMapping("/users/login")
	public ResponseEntity<User> loginUser(@Valid @RequestBody User user) {
		
		return new ResponseEntity<User>(uService.loginUser(user), HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return  new ResponseEntity<User>(uService.updateUser(user), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("/users/checkByEmail")
	public ResponseEntity<AccountResponse> resetPasswordEmail(@Valid @RequestBody ResetPassword resetPassword){

		//boolean result = this.uService.ifEmailExist(resetPassword.getEmail());
		User user = this.uService.getUserBuMail(resetPassword.getEmail());
		AccountResponse accountResponse = new AccountResponse();
		if ( user != null ){
			String code = UserCode.getCode();
			Mail email = new Mail(resetPassword.getEmail() , "http://localhost:63885//users/checkByEmail?token="+code);
			emailService.sendCodeByEmail(email);
			user.setCode(code);
			this.uService.editUser(user);
			accountResponse.setResult(1);

		} else {
				accountResponse.setResult(0);
		}

		return  new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.OK);

	}

	@CrossOrigin
	@PostMapping("/users/resetPassword")
	public ResponseEntity<AccountResponse> resetPassword(@RequestBody NewPassword newPassword){
		User user = this.uService.getUserBuMail(newPassword.getEmail());
		AccountResponse accountResponse = new AccountResponse();
		if(user != null){

			if (user.getCode().equals(newPassword.getCode())){
				user.setPassword(newPassword.getPassword());
				this.uService.editUser(user);
		     	accountResponse.setResult(1);
				

			} else {
				accountResponse.setResult(0);

			}

		} else {
			accountResponse.setResult(0);

		}

		return  new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.OK);

	}

}

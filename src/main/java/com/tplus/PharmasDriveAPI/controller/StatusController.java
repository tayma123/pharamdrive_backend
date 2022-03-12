package com.tplus.PharmasDriveAPI.controller;

import com.tplus.PharmasDriveAPI.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private StatusService statusService ;



    @GetMapping("/status/")
	public ResponseEntity<String> getStatus() {
		
		return new ResponseEntity<String>(statusService.getStatus(), HttpStatus.OK);
		
	}
    
}

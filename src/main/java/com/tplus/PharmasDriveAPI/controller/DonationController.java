package com.tplus.PharmasDriveAPI.controller;

import java.io.IOException;

import com.tplus.PharmasDriveAPI.model.Donation;
import com.tplus.PharmasDriveAPI.service.DonationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
public class DonationController {


    @Autowired
    private DonationService donationService ;


    @CrossOrigin
    @GetMapping(value="/donations")
    public ResponseEntity<Page<Donation>>  getDonations(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
         return new ResponseEntity<Page<Donation>>(donationService.getDonations(paging) , HttpStatus.OK);
}

    @PostMapping(value="/donations")
    public ResponseEntity<Donation> addDonation( @RequestPart String  client, @RequestPart("file") MultipartFile file, @RequestPart String  amount, @RequestPart String  receiver, @RequestPart String  dateHour) throws IOException {
	/*File myFile = new File(FILE_DIRECTORY + image.getOriginalFilename());
	myFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(myFile);
	fos.write(image.getBytes());
	fos.close();*/
	Donation donation = new Donation(client , file. getOriginalFilename(), amount, receiver, dateHour);
	
	return new ResponseEntity<Donation>(donationService.addDonation(donation), HttpStatus.CREATED);

}


    @PutMapping("/donations")
    public ResponseEntity<Donation> updateDonation(@RequestBody Donation donation) {
        return  new ResponseEntity<Donation>(donationService.updateDonation(donation), HttpStatus.OK);

    }



    @GetMapping("/donations/{id}")
public ResponseEntity<Donation> getUser(@PathVariable("id") String id) {
	
	return new ResponseEntity<Donation>(donationService.getDonation(id), HttpStatus.OK);
	
}
	
	
@DeleteMapping("/donations")
public ResponseEntity<HttpStatus> deleteDonation(@RequestParam String id) {
	
	donationService.deleteDonation(id);
	return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	
}
}
 
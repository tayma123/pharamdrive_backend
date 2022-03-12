package com.tplus.PharmasDriveAPI.controller;

import java.io.IOException;


import com.tplus.PharmasDriveAPI.model.Pharmacy;
import com.tplus.PharmasDriveAPI.service.PharmacyService;


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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import  org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
public class PharmacyController {

    @Autowired
    private PharmacyService pharService ;
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY ;

	@CrossOrigin
    @GetMapping("/pharmacies")
    public ResponseEntity<Page<Pharmacy>>  getPharmacies(@RequestParam(defaultValue = "0") int page,
	@RequestParam(defaultValue = "10") int size){
		Pageable paging = PageRequest.of(page, size);

        return new ResponseEntity<Page<Pharmacy>>(pharService.getPharmacies(paging) , HttpStatus.OK);
    }


    @PostMapping(value = "/pharmacies" )
    public ResponseEntity<Pharmacy> createUser( @RequestPart String  pharmacyName, @RequestPart("image") MultipartFile image, @RequestPart String  address, @RequestPart String  mobile, @RequestPart String  telephone) throws IOException {
		/*File myFile = new File(FILE_DIRECTORY + image.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(image.getBytes());
		fos.close();*/
		Pharmacy pharmacy = new Pharmacy(pharmacyName , image. getOriginalFilename(), address, mobile , telephone);
		
		return new ResponseEntity<Pharmacy>(pharService.createPharmacy(pharmacy), HttpStatus.CREATED);
	}

    @PutMapping("/pharmacies")
	public ResponseEntity<Pharmacy> updateUser(@RequestBody Pharmacy pharmacy) {
		return  new ResponseEntity<Pharmacy>(pharService.updatePharmacy(pharmacy), HttpStatus.OK);
	}



    @GetMapping("/pharmacies/{id}")
	public ResponseEntity<Pharmacy> getUser(@PathVariable("id") String id) {
		
		return new ResponseEntity<Pharmacy>(pharService.getPharmacy(id), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/pharmacies")
	public ResponseEntity<HttpStatus> deleteUser(@RequestParam String id) {
		
		pharService.deletePharmacy(id);
		return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	

	}


	@CrossOrigin
@GetMapping("/pharmacies/count")
public ResponseEntity<Integer> getOrdersCount() {
	
	return new ResponseEntity<Integer>(pharService.countOrders(), HttpStatus.OK);
	
}

    


    
    
}

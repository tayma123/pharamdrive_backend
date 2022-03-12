package com.tplus.PharmasDriveAPI.controller;

import java.io.IOException;


import com.tplus.PharmasDriveAPI.model.Publicity;
import com.tplus.PharmasDriveAPI.service.PublicityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PublicityController {


    @Autowired
    private PublicityService publicityService ;


    @CrossOrigin
    @GetMapping(value="/publicities")
    public ResponseEntity<Page<Publicity>>  getPublicities(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
         return new ResponseEntity<Page<Publicity>>(publicityService.getPublicities(paging) , HttpStatus.OK);
}

    @PostMapping(value="/publicities")
    public ResponseEntity<Publicity> addPublicity( @RequestBody Publicity publicity) throws IOException {
	/*File myFile = new File(FILE_DIRECTORY + image.getOriginalFilename());
	myFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(myFile);
	fos.write(image.getBytes());
	fos.close();*/
	//Order order = new Order(userName , amount, drog, dateHour);
	
	return new ResponseEntity<Publicity>(publicityService.addPublicity(publicity), HttpStatus.CREATED);

}


    @PutMapping("/publicities")
    public ResponseEntity<Publicity> updatePublicity(@RequestBody Publicity publicity) {
        return  new ResponseEntity<Publicity>(publicityService.updatePublicity(publicity), HttpStatus.OK);

    }



    @GetMapping("/publicities/{id}")
public ResponseEntity<Publicity> gePublicity(@PathVariable("id") String id) {
	
	return new ResponseEntity<Publicity>(publicityService.getPublicity(id), HttpStatus.OK);
	
}
	
	
@DeleteMapping("/publicities")
public ResponseEntity<HttpStatus> deletePublicity(@RequestParam String id) {
	
	publicityService.deletePublicity(id);
	return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	
}
}
 


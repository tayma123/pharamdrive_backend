package com.tplus.PharmasDriveAPI.controller;

import java.io.IOException;


import com.tplus.PharmasDriveAPI.model.Delivery;
import com.tplus.PharmasDriveAPI.service.DeliveryService;

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
public class DeliveryController {


    @Autowired
    private DeliveryService deliveryService ;


    @CrossOrigin
    @GetMapping(value="/deliveries")
    public ResponseEntity<Page<Delivery>>  getDeliveries(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
         return new ResponseEntity<Page<Delivery>>(deliveryService.getDeliveries(paging) , HttpStatus.OK);
}

    @PostMapping(value="/deliveries")
    public ResponseEntity<Delivery> addDelivery( @RequestBody Delivery delivery) throws IOException {
	/*File myFile = new File(FILE_DIRECTORY + image.getOriginalFilename());
	myFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(myFile);
	fos.write(image.getBytes());
	fos.close();*/
	//Order order = new Order(userName , amount, drog, dateHour);
	
	return new ResponseEntity<Delivery>(deliveryService.addDelivery(delivery), HttpStatus.CREATED);

}


    @PutMapping("/deliveries")
    public ResponseEntity<Delivery> updateDelivery(@RequestBody Delivery delivery) {
        return  new ResponseEntity<Delivery>(deliveryService.updateDelivery(delivery), HttpStatus.OK);

    }



    @GetMapping("/deliveries/{id}")
public ResponseEntity<Delivery> getDelivery(@PathVariable("id") String id) {
	
	return new ResponseEntity<Delivery>(deliveryService.getDelivery(id), HttpStatus.OK);
	
}
	
	
@DeleteMapping("/deliveries")
public ResponseEntity<HttpStatus> deleteDelivery(@RequestParam String id) {
	
	deliveryService.deleteDelivery(id);
	return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	
}
}
 

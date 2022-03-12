package com.tplus.PharmasDriveAPI.controller;

import java.io.IOException;


import com.tplus.PharmasDriveAPI.model.Order;
import com.tplus.PharmasDriveAPI.model.Status;
import com.tplus.PharmasDriveAPI.service.OrderService;

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
public class OrderController {


    @Autowired
    private OrderService OrderService ;


    @CrossOrigin
    @GetMapping(value="/orders")
    public ResponseEntity<Page<Order>>  getOrders(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
         return new ResponseEntity<Page<Order>>(OrderService.getOrders(paging) , HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping(value="/orders/countFiltredByStatus/{status}")
    public ResponseEntity<Long>  getCountOrdersByStatusFilter(@PathVariable("status") String status)  {
        
        return new ResponseEntity<Long>(OrderService.getCountOrdersByStatusFilter(status) , HttpStatus.OK);
    }

    
    @CrossOrigin

    @PostMapping(value="/orders/")
    public ResponseEntity<Order> addOrder( @RequestBody Order order) throws IOException {
	/*File myFile = new File(FILE_DIRECTORY + image.getOriginalFilename());
	myFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(myFile);
	fos.write(image.getBytes());
	fos.close();*/
	//Order order = new Order(userName , amount, drog, dateHour);
	
	return new ResponseEntity<Order>(OrderService.addOrder(order), HttpStatus.CREATED);

}


    @CrossOrigin
    @PutMapping("/orders")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return  new ResponseEntity<Order>(OrderService.updateOrder(order), HttpStatus.OK);

    }



    @GetMapping("/orders/{id}")
public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
	
	return new ResponseEntity<Order>(OrderService.getOrder(id), HttpStatus.OK);
	
}
	
@CrossOrigin
	
@DeleteMapping("/orders")
public ResponseEntity<HttpStatus> deleteOrder(@RequestParam String id) {
	
	OrderService.deleteOrder(id);
	return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	
}
@CrossOrigin
@GetMapping("/orders/count")
public ResponseEntity<Integer> getOrdersCount() {
	
	return new ResponseEntity<Integer>(OrderService.countOrders(), HttpStatus.OK);
	
}



}
 

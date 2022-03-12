package com.tplus.PharmasDriveAPI.controller;

import java.io.IOException;


import com.tplus.PharmasDriveAPI.model.Invoice;
import com.tplus.PharmasDriveAPI.service.InvoiceService;

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
public class InvoiceController {


    @Autowired
    private InvoiceService invoiceService ;


    @CrossOrigin
    @GetMapping(value="/invoices")
    public ResponseEntity<Page<Invoice>>  getInvoices(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
         return new ResponseEntity<Page<Invoice>>(invoiceService.getInvoices(paging) , HttpStatus.OK);
}

    @PostMapping(value="/invoices")
    public ResponseEntity<Invoice> addInvoice( @RequestBody Invoice invoice) throws IOException {
	/*File myFile = new File(FILE_DIRECTORY + image.getOriginalFilename());
	myFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(myFile);
	fos.write(image.getBytes());
	fos.close();*/
	//Order order = new Order(userName , amount, drog, dateHour);
	
	return new ResponseEntity<Invoice>(invoiceService.addInvoice(invoice), HttpStatus.CREATED);

}


    @PutMapping("/invoices")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice) {
        return  new ResponseEntity<Invoice>(invoiceService.updateInvoice(invoice), HttpStatus.OK);

    }



    @GetMapping("/invoices/{id}")
public ResponseEntity<Invoice> geInvoice(@PathVariable("id") String id) {
	
	return new ResponseEntity<Invoice>(invoiceService.getInvoice(id), HttpStatus.OK);
	
}
	
	
@DeleteMapping("/invoices")
public ResponseEntity<HttpStatus> deleteInvoice(@RequestParam String id) {
	
	invoiceService.deleteInvoice(id);
	return new  ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	
}
}
 


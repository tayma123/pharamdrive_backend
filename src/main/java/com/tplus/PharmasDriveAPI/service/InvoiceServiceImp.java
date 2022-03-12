package com.tplus.PharmasDriveAPI.service;

import java.util.Optional;

import com.tplus.PharmasDriveAPI.model.Invoice;
import com.tplus.PharmasDriveAPI.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class InvoiceServiceImp  implements InvoiceService{


    @Autowired
    private InvoiceRepository invoiceRepository ;

    @Override
    public Page<Invoice> getInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
        
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
        
    }

    @Override
    public Invoice getInvoice(String id) {
        Optional<Invoice> invoice =  invoiceRepository.findById(id);
        if (invoice.isPresent())
            return invoice.get();
        throw new RuntimeException("Donation is not found for the id "+ id);
		
    }

    @Override
    public void deleteInvoice(String id){
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}

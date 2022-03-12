package com.tplus.PharmasDriveAPI.service;
import com.tplus.PharmasDriveAPI.model.Invoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {

    Page<Invoice> getInvoices(Pageable pageable);
    Invoice addInvoice(Invoice invoice);
    Invoice  getInvoice(String id );
    void deleteInvoice(String id );
    Invoice updateInvoice(Invoice invoice);
}

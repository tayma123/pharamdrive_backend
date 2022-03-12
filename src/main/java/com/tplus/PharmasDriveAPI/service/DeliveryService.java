package com.tplus.PharmasDriveAPI.service;
import com.tplus.PharmasDriveAPI.model.Delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {

    Page<Delivery> getDeliveries(Pageable pageable);
    Delivery addDelivery(Delivery delivery);
    Delivery  getDelivery(String id );
    void deleteDelivery(String id );
    Delivery updateDelivery(Delivery delivery);
}

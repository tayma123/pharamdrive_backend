package com.tplus.PharmasDriveAPI.service;

import java.util.Optional;

import com.tplus.PharmasDriveAPI.model.Delivery;
import com.tplus.PharmasDriveAPI.repository.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class DeliveryServiceImp  implements DeliveryService{


    @Autowired
    private DeliveryRepository deliveryRepository ;

    @Override
    public Page<Delivery> getDeliveries(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
        
    }

    @Override
    public Delivery addDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
        
    }

    @Override
    public Delivery getDelivery(String id) {
        Optional<Delivery> delivery =  deliveryRepository.findById(id);
        if (delivery.isPresent())
            return delivery.get();
        throw new RuntimeException("Donation is not found for the id "+ id);
		
    }

    @Override
    public void deleteDelivery(String id){
        deliveryRepository.deleteById(id);
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
}
